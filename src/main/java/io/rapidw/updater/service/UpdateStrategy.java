package io.rapidw.updater.service;

import io.rapidw.updater.Configuration;

public interface UpdateStrategy {

    boolean requireUpdate(Configuration oldConfig, Configuration newConfig);
}
