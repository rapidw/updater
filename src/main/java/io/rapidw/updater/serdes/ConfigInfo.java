package io.rapidw.updater.serdes;

import lombok.Data;

import java.util.List;

@Data
public class ConfigInfo {
    private Base base;
    private List<Property> properties;
    private List<FileMetadata> fileMetadata;
}
