package ta.core.env;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.File;

@Log4j2
public class Environment {

    @Setter
    private static String environment;
    private static Config currentConfig;

    private Environment() {
        if (environment.isEmpty()) {
            log.debug("environment name is empty");
            throw new ExceptionInInitializerError(
                    "please choose environment name from src/test/resources/environment.conf file");
        } else {
            log.debug("getting configs from <{}>", environment);
            var config = ConfigFactory.parseFile(new File("src/test/resources/environment.conf"));
            var defaultEnv = config.getConfig("env.default");
            currentConfig = config.getConfig("env." + environment).withFallback(defaultEnv);
        }
    }

    public static Environment getCurrentEnvironment() {
        return new Environment();
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String path, T defaultValue) {
        log.debug("getting value by path <{}>", path);
        if (currentConfig.hasPath(path)) {
            T value = (T) currentConfig.getAnyRef(path);
            log.debug("<{}> value is: <{}>", path, value);
            return value;
        } else {
            log.debug("cannot find path <{}> returning default value: <{}>", path, defaultValue);
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) currentConfig.getAnyRef(path);
    }

}
