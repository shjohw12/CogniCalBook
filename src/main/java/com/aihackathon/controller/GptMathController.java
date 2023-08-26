package com.aihackathon.controller;

import com.aihackathon.dto.GptMathRequest;
import com.aihackathon.dto.GptMathResponse;
import com.aihackathon.service.CreateFileService;
import com.aihackathon.service.GPTService;
import com.aihackathon.service.MathImageConvertService;
import com.aihackathon.service.TextImageConvertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GptMathController {

    private final CreateFileService createFileService;

    private final MathImageConvertService mathImageConvertService;

    private final TextImageConvertService textImageConvertService;

    private final GPTService gptService;

    @GetMapping("/api/gpt-math")
    public ResponseEntity<GptMathResponse> getGptMathResponse(@Valid @RequestBody GptMathRequest gptMathRequest) throws Exception {

        createFileService.createFile(gptMathRequest.getFileEncodedBase64(), gptMathRequest.getFilename());

        String problemText = gptMathRequest.isText() ? textImageConvertService.clova(gptMathRequest.getFilename(), gptMathRequest.getFileEncodedBase64()) : mathImageConvertService.mathImageConvert(gptMathRequest.getFilename());

        String gptHint = gptService.getGPTString(problemText);

        return ResponseEntity.ok(GptMathResponse.builder().gpt(gptHint).build());
        //
//        // GPT API 사용
//        String gpt = "hi";
//
//        return ResponseEntity.ok(GptMathResponse.builder().gpt(gpt).build());
    }

    @GetMapping("/api/test")
    public String hello() {
        return "hello";
    }
}
