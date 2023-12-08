package io.rapidw.updater.service;

import io.rapidw.updater.serdes.File;

public interface RemoteFileHandler {

    void download(File file);
}
