package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.File;

import java.util.List;

public interface FileService {
    File save(File file, String token);

    File getById(Long id);

    List<File> getAll();

    File findByFileName(String fileName);

    File deleteById(Long id, String token);
}
