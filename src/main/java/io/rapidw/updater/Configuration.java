package io.rapidw.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.rapidw.updater.serdes.UpdateInfo;
import io.rapidw.updater.service.RemoteFileHandler;
import lombok.val;

import java.nio.file.Paths;

public class Configuration {

    private final UpdateInfo updateInfo;

    public Configuration(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
        processPaths();
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

    public void backup() {
        // 备份原有配置
        // 备份原有文件
    }

    public void restore() {
        // 写入新配置
        // 获取所有新文件
    }

    public void downloadFiles(RemoteFileHandler remoteFileHandler) {
        updateInfo.getFiles().forEach(remoteFileHandler::download);
    }

    private void processPaths() {
        for (val file : updateInfo.getFiles()) {
            if (!Paths.get(file.getPath()).isAbsolute()) {
                file.setPath(Paths.get(updateInfo.getConfig().getBasePath(), file.getPath()).toString());
            }
        }
    }
}
