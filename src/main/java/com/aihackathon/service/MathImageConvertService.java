package com.aihackathon.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MathImageConvertService {

    private static String PYTHON_FILE = "/home/ubuntu/image-to-latex/app.py";

    public String mathImageConvert(String filename) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", PYTHON_FILE, filename);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
