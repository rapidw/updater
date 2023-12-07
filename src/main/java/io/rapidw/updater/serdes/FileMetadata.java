package io.rapidw.updater.serdes;

import lombok.Data;

@Data
public class FileMetadata {
    private String uri;
    private String path;
    private String checksum;
    private Long size;
    private Boolean inClasspath;
}
