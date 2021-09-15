package com.maltsevve.springBootApp.rest.files;

import com.maltsevve.springBootApp.dto.UserFileDto;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.service.FileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users/files")
@RequiredArgsConstructor
@Api(tags = { "Files: user access" })
public class UserFileRestControllerV1 {
    private final FileService fileService;

    @SneakyThrows
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URL> getFiles(@PathVariable("id") Long fileId) {
        if (Objects.isNull(fileId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File file = this.fileService.getById(fileId);

        if (Objects.isNull(file)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new URL(file.getFileUrl()), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserFileDto>> getAllFiles() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserFileDto.fromFiles(files), HttpStatus.OK);
    }
}
