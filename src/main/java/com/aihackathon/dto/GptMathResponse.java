package com.aihackathon.dto;

import lombok.Builder;
import lombok.Value;
import org.json.simple.parser.JSONParser;

@Value
@Builder
public class GptMathResponse {
    String gpt;
}
