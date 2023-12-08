package io.rapidw.updater.serdes;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateInfo {
    private Base base;
    private Map<String, String> properties;
    private List<File> fileMetadata;
}
