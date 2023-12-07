package io.rapidw.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.rapidw.updater.serdes.ConfigInfo;
import lombok.val;

public class Updater {

    public static Configuration loadFromJson(String string) {
        val mapper = new ObjectMapper();
        try {
            val configInfo = mapper.readValue(string, ConfigInfo.class);
            return new Configuration(configInfo);
        } catch (Exception e) {
            throw new UpdaterException(e);
        }
    }

    public static String storeAsJson(ConfigInfo configInfo) {
        val mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(configInfo);
        } catch (Exception e) {
            throw new UpdaterException(e);
        }
    }
}
