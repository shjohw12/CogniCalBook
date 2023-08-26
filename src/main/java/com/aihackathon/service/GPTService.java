
package com.aihackathon.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final ChatgptService chatgptService;

    public String getGPTHint1(String prompt) {
        String newPrompt = "아래 수학문제에서 주요하게 다루는 수학 개념은 무엇인가\n"
            + "상세한 문제 해설과 답을 생략하라.\n"
            + " [일차방정식,이차방정식,극한,미분,적분,경우의 수,순열,조합] 중 한 개의 수학 개념 용어로 단답형으로 응답하라\n"
            + "```\n" + prompt + "\n```\n";
        return getGPTString(newPrompt);
    }

    public String getGPTHint2(String prompt) {
        String newPrompt = "다음 수학 문제를 풀 때 필요한 조건이나 공식을 알려줘\n"
            + "일반화된 공식이나 조건만 서술하시오 \n"
            + "계산 금지.\n" + "```\n" + prompt + "\n```\n";
        return getGPTString(newPrompt);
    }

    public String getGPTHint3(String prompt) {
        String newPrompt = "짧은 풀이와 답을 알려줘.\n\n" + prompt;
        return getGPTString(newPrompt);
    }

    public String getGPTAnswer(String prompt) {
        String newPrompt = "다음 수학 문제의 정답만 알려줘.\n" + "```\n" + prompt + "\n```\n";
        return getGPTString(newPrompt);
    }


    public String getGPTString(String prompt){
        return chatgptService.sendMessage(prompt);
    }

}

