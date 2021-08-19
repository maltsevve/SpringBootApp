package com.maltsevve.springBootApp.rest.files;

import com.maltsevve.springBootApp.dto.AdminFileDto;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.service.FileService;
import com.maltsevve.springBootApp.service.amazon.AmazonClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/moderators/files")
@RequiredArgsConstructor
public class ModerFileRestControllerV1 {
    private final FileService fileService;
    private final AmazonClient amazonClient;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminFileDto> saveFile(@RequestHeader(value = "Authorization") String token,
                                                 @RequestPart(value = "file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();

        if (Objects.isNull(file)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String fileUrl = this.amazonClient.uploadFile(file);

        File savedFile = new File();
        savedFile.setFileName(file.getOriginalFilename());
        savedFile.setFileUrl(fileUrl);
        this.fileService.save(savedFile, token.substring(7));

        return new ResponseEntity<>(AdminFileDto.fromFile(savedFile), headers, HttpStatus.CREATED);
    }

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
    public ResponseEntity<List<AdminFileDto>> getAllFiles() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(AdminFileDto.fromFiles(files), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<File> deleteFile(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable("id") Long fileId) {
        File file = fileService.getById(fileId);

        if (Objects.isNull(file)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.amazonClient.deleteFileFromS3Bucket(file.getFileName());
        this.fileService.deleteById(fileId, token.substring(7));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
