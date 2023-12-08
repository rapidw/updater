package io.rapidw.updater.serdes;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateInfo {
    private Config config;
    private Map<String, String> properties;
    private List<File> files;
}
