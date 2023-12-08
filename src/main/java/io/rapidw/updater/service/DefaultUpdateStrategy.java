package io.rapidw.updater.service;

import io.rapidw.updater.Configuration;

public class DefaultUpdateStrategy implements UpdateStrategy {
    @Override
    public boolean requireUpdate(Configuration oldConfig, Configuration newConfig) {
        return false;
    }
}
