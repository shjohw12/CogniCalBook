package com.aihackathon.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClovaRequest {

    @NotBlank
    private String version;

    @NotBlank
    private String requestId;

    @NotBlank
    private Long timestamp;

    @NotBlank
    private ArrayList<ClovaImageEntity> images;

    public ClovaRequest(String version, String requestId, Long timestamp,
        ArrayList<ClovaImageEntity> images) {
        this.version = version;
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.images = images;
    }

    @Override
    public String toString() {
        return "{" +
            "\"version\":\"" + version + '\"' +
            ", \"requestId\":\"" + requestId + '\"' +
            ", \"timestamp\":" + timestamp +
            ", \"images\":" + images +
            '}';
    }
}


