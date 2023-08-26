package com.aihackathon.controller;

import com.aihackathon.dto.GptMathRequest;
import com.aihackathon.dto.GptMathResponse;
import com.aihackathon.service.CreateFileService;
import com.aihackathon.service.MathImageConvertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GptMathController {

    private final CreateFileService createFileService;

    private final MathImageConvertService mathImageConvertService;

    @GetMapping("/api/gpt-math")
    public ResponseEntity<GptMathResponse> getGptMathResponse(@Valid @RequestBody GptMathRequest gptMathRequest) throws Exception {
        createFileService.createFile(gptMathRequest.getFileEncodedBase64(), gptMathRequest.getFilename());

        String problemText = gptMathRequest.isText() ? null : mathImageConvertService.mathImageConvert(gptMathRequest.getFilename());

        // GPT API 사용

        return ResponseEntity.ok(GptMathResponse.builder()
                        .problemText(problemText)
                        .hint1("hint1")
                        .hint2("hint2")
                        .hint3("hint3")
                        .build());

    }

    @GetMapping("/api/sample")
    public ResponseEntity<GptMathResponse> sample(@Valid @RequestBody GptMathRequest gptMathRequest) throws Exception {
        return ResponseEntity.ok(GptMathResponse.builder()
                .problemText("problemText")
                .hint1("hint1")
                .hint2("hint2")
                .hint3("hint3")
                .answer("answer")
                .build());
    }

    @GetMapping("/api/test")
    public String hello() {
        return "hello";
    }
}
