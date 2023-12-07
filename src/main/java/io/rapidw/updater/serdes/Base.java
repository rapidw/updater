package io.rapidw.updater.serdes;

import lombok.Data;

import java.time.Instant;

@Data
public class Base {
    private Instant timestamp;
    private String baseUri;
    private String basePath;
    private String updateHandler;
    private String launcher;
}
