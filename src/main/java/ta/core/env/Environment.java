package ta.core.env;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.File;

@Log4j2
public class Environment {

    @Setter
    @Getter
    private static String environment;

    public static Config get() {
        if (environment.isEmpty()) {
            log.debug("environment name is empty");
            throw new ExceptionInInitializerError(
                    "please choose environment name from src/test/resources/environment.conf file");
        } else {
            log.debug("getting configs from <{}>", environment);
            Config config = ConfigFactory.parseFile(new File("src/test/resources/environment.conf"));
            Config defaultEnv = config.getConfig("env.default");
            return config.getConfig("env." + environment).withFallback(defaultEnv);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValueOrDefault(String path, T defaultValue) {
        log.debug("getting value by path <{}>", path);
        if (Environment.get().hasPath(path)) {
            T value = (T) Environment.get().getAnyRef(path);
            log.debug("<{}> value is: <{}>", path, value);
            return value;
        } else {
            log.debug("cannot find path <{}> returning default value: <{}>", path, defaultValue);
            return defaultValue;
        }
    }

}
