package io.rapidw.updater.serdes;

import lombok.Data;

import java.util.Map;

@Data
public class File {
    private String protocol;
    private String uri;
    private String path;
    private String checksum;
    private Long size;
    private Boolean inClasspath;
    private Map<String, String> options;
}
