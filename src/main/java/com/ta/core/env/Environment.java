package com.ta.core.env;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.Map;

@Log4j2
public class Environment {

    @Setter
    private static String environment;

    public static Config get() {
        if (environment.isEmpty()) {
            log.debug("environment name is empty");
            throw new ExceptionInInitializerError("please choose environment name from src/test/resources/environment.conf file");
        } else {
            log.debug("getting configs from <" + environment + ">");
            Config config = ConfigFactory.parseFile(new File("src/test/resources/environment.conf"));
            Config defaultEnv = config.getConfig("env.default");
            return config.getConfig("env." + environment).withFallback(defaultEnv);
        }
    }

    public static Map<String, Object> getObjectOrDefault(String path, Map<String, Object> defaultValue) {
        if (Environment.get().hasPath(path)) {
            return Environment.get().getObject(path).unwrapped();
        } else {
            return defaultValue;
        }
    }

    public static int getIntOrDefault(String path, int defaultValue) {
        if (Environment.get().hasPath(path)) {
            return Environment.get().getInt(path);
        } else {
            return defaultValue;
        }
    }

    public static boolean getBooleanOrDefault(String path, boolean defaultValue) {
        if (Environment.get().hasPath(path)) {
            return Environment.get().getBoolean(path);
        } else {
            return defaultValue;
        }
    }

}
