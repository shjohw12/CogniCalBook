package com.aihackathon.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GptMathResponse {
    String problemText;

    String hint1;

    String hint2;

    String hint3;

    String answer;
}
