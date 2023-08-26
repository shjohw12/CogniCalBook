package com.aihackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClovaImageEntity {

    @NotBlank
    private String format;

    @NotBlank
    private String data;

    @NotBlank
    private String name;

    public ClovaImageEntity(String format, String data, String name) {
        this.format = format;
        this.data = data;
        this.name = name;
    }


    @Override
    public String toString() {
        return "{" +
            "\"format\":\"" + format + "\"" +
            ", \"data\":\"" + data + "\"" +
            ", \"name\":\"" + name + '\"' +
            '}';
    }
}
