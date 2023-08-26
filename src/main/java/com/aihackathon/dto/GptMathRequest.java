package com.aihackathon.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GptMathRequest {
    String filename;

    String fileEncodedBase64;

    @Builder.Default
    boolean isText = false;
}
