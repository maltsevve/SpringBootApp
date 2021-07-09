package com.maltsevve.springBootApp.rest;

import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files/")
public class FileRestControllerV1 {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> saveFile(@RequestBody @Validated File file) {
        HttpHeaders headers = new HttpHeaders();

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.fileService.save(file);
        return new ResponseEntity<>(file, headers, HttpStatus.CREATED);
    }


    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> updateFile(@RequestBody @Validated File file) {
        HttpHeaders headers = new HttpHeaders();

        if (fileService.getById(file.getId()) != null)

            if (file.getId() == null) {                        /** Нужно ли проверять наличие id или только file'а?*/
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        this.fileService.save(file);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }


    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> getFiles(@PathVariable("id") Long fileId) {
        if (fileId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File file = this.fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> deleteFile(@PathVariable("id") Long fileId) {
        File file = fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.fileService.deleteById(fileId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
