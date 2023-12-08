package io.rapidw.updater;

import io.rapidw.updater.serdes.UpdateInfo;
import io.rapidw.updater.serdes.File;
import io.rapidw.updater.service.DefaultLauncher;
import io.rapidw.updater.service.DefaultUpdateStrategy;
import io.rapidw.updater.service.Launcher;
import io.rapidw.updater.service.UpdateStrategy;
import lombok.Builder;
import lombok.val;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

public class Updater {

    private final UpdateStrategy updateStrategy;
    private final Launcher launcher;

    private Configuration oldConfig;
    private Configuration newConfig;

    @Builder
    public Updater(Launcher launcher, UpdateStrategy updateStrategy) {
        this.launcher = Objects.requireNonNullElseGet(launcher, () -> ServiceLoader.load(Launcher.class).findFirst().orElse(new DefaultLauncher()));
        this.updateStrategy = new DefaultUpdateStrategy();
    }

    public boolean isUpdateRequired() {
        return updateStrategy.requireUpdate(oldConfig, newConfig);
    }

    public boolean doUpdate() {
        // 备份原有配置
        // 备份原有文件
        // 写入新配置
        // 获取所有新文件
        return true;
    }

    public void launchApplication() {

    }

    public void backup() {

    }

    public void rollBack() {

    }


    private List<File> collectOldFiles(UpdateInfo updateInfo) {
        val oldFiles = new ArrayList<File>();
        for (val newFile : updateInfo.getFileMetadata()) {
            val oldFile = new File();
            val path = newFile.getPath();
            if (Paths.get(path).isAbsolute()) {
                oldFile.setPath(newFile.getPath());
            } else {
                oldFile.setPath(Paths.get(updateInfo.getBase().getBasePath(), newFile.getPath()).toString());
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
