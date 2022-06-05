package ta.core.env;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.log4j.Log4j2;
import ta.core.utils.FileUtil;

@Log4j2
public class Environment {

    private static final ThreadLocal<String> ENVIRONMENT = new ThreadLocal<>();
    private static final ThreadLocal<Config> CURRENT_CONFIG = new ThreadLocal<>();

    public static void setEnvironment(String env) {
        ENVIRONMENT.set(env);
        if (ENVIRONMENT.get().isEmpty()) {
            log.debug("environment name is empty");
            throw new ExceptionInInitializerError(
                    "please choose environment name from environment.conf file");
        } else {
            log.debug("getting configs from <{}>", ENVIRONMENT.get());
            var configFile = FileUtil.findFile("environment", "conf");
            var config = ConfigFactory.parseFile(configFile);
            var defaultEnv = config.getConfig("env.default");
            CURRENT_CONFIG.set(config.getConfig("env." + ENVIRONMENT.get()).withFallback(defaultEnv));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getOrDefault(String path, T defaultValue) {
        log.debug("getting value by path <{}>", path);
        if (CURRENT_CONFIG.get().hasPath(path)) {
            T value = (T) CURRENT_CONFIG.get().getAnyRef(path);
            log.debug("<{}> value is: <{}>", path, value);
            return value;
        } else {
            log.debug("cannot find path <{}> returning default value: <{}>", path, defaultValue);
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String path) {
        return (T) CURRENT_CONFIG.get().getAnyRef(path);
    }
}
