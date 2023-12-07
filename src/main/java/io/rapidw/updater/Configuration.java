package io.rapidw.updater;

import io.rapidw.updater.serdes.ConfigInfo;
import io.rapidw.updater.serdes.FileMetadata;
import io.rapidw.updater.service.DefaultUpdateStrategy;
import io.rapidw.updater.service.UpdateStrategy;
import lombok.val;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private final ConfigInfo configInfo;
    private final UpdateStrategy updateStrategy;

    public Configuration(ConfigInfo configInfo) {
        this.configInfo = configInfo;
        this.updateStrategy = new DefaultUpdateStrategy();
    }

    public boolean requireUpdate() {
        val oldFiles = collectOldFiles(configInfo);
        return updateStrategy.requireUpdate(configInfo, oldFiles);
    }

    public void doUpdate() {

    }

    public void launch() {

    }


    private List<FileMetadata> collectOldFiles(ConfigInfo configInfo) {
        val oldFiles = new ArrayList<FileMetadata>();
        for (val newFile : configInfo.getFileMetadata()) {
            val oldFile = new FileMetadata();
            val path = newFile.getPath();
            if (Paths.get(path).isAbsolute()) {
                oldFile.setPath(newFile.getPath());
            } else {
                oldFile.setPath(Paths.get(configInfo.getBase().getBasePath(), newFile.getPath()).toString());
            }
            oldFile.setChecksum(calculateChecksum(oldFile.getPath()));
            oldFile.setSize(Paths.get(oldFile.getPath()).toFile().length());
            oldFiles.add(oldFile);
        }
        return oldFiles;
    }

    private String calculateChecksum(String path) {
        return "";
    }
}
