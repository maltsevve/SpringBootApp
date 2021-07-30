package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.File;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFileDto {
    private Long id;
    private String fileName;

    public File toFile() {
        File file = new File();
        file.setId(id);
        file.setFileName(fileName);

        return file;
    }

    public static UserFileDto fromFile(File file) {
        UserFileDto userFileDto = new UserFileDto();
        userFileDto.setId(file.getId());
        userFileDto.setFileName(file.getFileName());

        return userFileDto;
    }

    public static List<UserFileDto> fromFiles(List<File> files) {
        return files.stream().map(UserFileDto::fromFile).collect(Collectors.toList());
    }
}
