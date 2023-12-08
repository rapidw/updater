package io.rapidw.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.rapidw.updater.serdes.UpdateInfo;
import lombok.val;

public class Configuration {

    private final UpdateInfo updateInfo;

    public Configuration(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;


    }

    public static Configuration loadFromJson(String string) {
        val mapper = new ObjectMapper();
        try {
            val configInfo = mapper.readValue(string, UpdateInfo.class);
            return new Configuration(configInfo);
        } catch (Exception e) {
            throw new UpdaterException(e);
        }
    }

    public static String storeAsJson(UpdateInfo updateInfo) {
        val mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(updateInfo);
        } catch (Exception e) {
            throw new UpdaterException(e);
        }
    }

    public void loadConfiguration(Configuration oldConfig, Configuration newConfig) {

    }




}
