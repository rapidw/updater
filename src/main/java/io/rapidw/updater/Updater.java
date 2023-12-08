package io.rapidw.updater;

import io.rapidw.updater.serdes.File;
import io.rapidw.updater.serdes.UpdateInfo;
import io.rapidw.updater.service.Launcher;
import io.rapidw.updater.service.RemoteFileHandler;
import io.rapidw.updater.service.UpdateStrategy;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Builder)
public class Updater {

    private final UpdateStrategy updateStrategy;
    private final RemoteFileHandler remoteFileHandler;
    private final Launcher launcher;

    private Configuration oldConfig;
    private Configuration newConfig;

    public void loadConfiguration(Configuration oldConfig, Configuration newConfig) {
        this.oldConfig = oldConfig;
        this.newConfig = newConfig;
    }

    public boolean isUpdateRequired() {
        return updateStrategy.requireUpdate(oldConfig, newConfig);
    }

    public void doUpdate() {
        newConfig.downloadFiles();
    }

    public void launchApplication() {

    }

    public void backup() {
        oldConfig.backup();
    }

    public void restore() {
        oldConfig.restore();
    }


    private List<File> collectOldFiles(UpdateInfo updateInfo) {
        val oldFiles = new ArrayList<File>();
        for (val newFile : updateInfo.getFiles()) {
            val oldFile = new File();
            val path = newFile.getPath();
            if (Paths.get(path).isAbsolute()) {
                oldFile.setPath(newFile.getPath());
            } else {
                oldFile.setPath(Paths.get(updateInfo.getConfig().getBasePath(), newFile.getPath()).toString());
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
