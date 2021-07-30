package com.maltsevve.springBootApp.rest.files;

import com.maltsevve.springBootApp.dto.AdminFileDto;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/moderators/files")
public class ModerFileRestControllerV1 {
    private final FileService fileService;

    @Autowired
    public ModerFileRestControllerV1(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> saveFile(@RequestBody @Valid File file) {
        HttpHeaders headers = new HttpHeaders();

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.fileService.save(file);
        return new ResponseEntity<>(file, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminFileDto> getFiles(@PathVariable("id") Long fileId) {
        if (fileId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File file = this.fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(AdminFileDto.fromFile(file), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminFileDto>> getAllFiles() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(AdminFileDto.fromFiles(files), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> deleteFile(@PathVariable("id") Long fileId) {
        File file = fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.fileService.deleteById(fileId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
