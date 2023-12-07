package io.rapidw.updater.service;

import io.rapidw.updater.serdes.ConfigInfo;
import io.rapidw.updater.serdes.FileMetadata;

import java.util.List;

public class DefaultUpdateStrategy implements UpdateStrategy {
    @Override
    public boolean requireUpdate(ConfigInfo configInfo, List<FileMetadata> oldFileMetadata) {
        return false;
    }
}
