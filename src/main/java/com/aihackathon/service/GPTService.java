
package com.aihackathon.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final ChatgptService chatgptService;

    public String getGPTString(String prompt){
        return chatgptService.sendMessage(prompt);
    }

}

