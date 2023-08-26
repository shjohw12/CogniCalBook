package com.aihackathon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class CreateFileService {

    public void createFile(String fileEncodedBase64, String filename) {
        try {
            var decoded =  Base64.getDecoder().decode(fileEncodedBase64);
            File file = new File(filename);
            file.createNewFile();
            var os = new FileOutputStream(file);
            os.write(decoded);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
