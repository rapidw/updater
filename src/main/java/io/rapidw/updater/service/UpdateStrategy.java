package io.rapidw.updater.service;

import io.rapidw.updater.serdes.ConfigInfo;
import io.rapidw.updater.serdes.FileMetadata;

import java.util.List;

public interface UpdateStrategy {

    boolean requireUpdate(ConfigInfo configInfo, List<FileMetadata> oldFileMetadata);
}
