package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.model.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminFileDto {
    private Long id;
    private String fileName;
    private Date created;
    private Date updated;
    private Status status;

    public File toFile() {
        File file = new File();
        file.setId(id);
        file.setFileName(fileName);
        file.setCreated(created);
        file.setUpdated(updated);
        file.setStatus(status);

        return file;
    }

    public static AdminFileDto fromFile(File file) {
        AdminFileDto adminFileDto = new AdminFileDto();
        adminFileDto.setId(file.getId());
        adminFileDto.setFileName(file.getFileName());
        adminFileDto.setCreated(file.getCreated());
        adminFileDto.setUpdated(file.getUpdated());
        adminFileDto.setStatus(file.getStatus());

        return adminFileDto;
    }

    public static List<AdminFileDto> fromFiles(List<File> files) {
        return files.stream().map(AdminFileDto::fromFile).collect(Collectors.toList());
    }
}
