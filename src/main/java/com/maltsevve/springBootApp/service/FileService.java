package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.File;

import java.util.List;

public interface FileService extends GenericService<File, Long> {
    @Override
    File save(File file);

    @Override
    File getById(Long id);

    @Override
    List<File> getAll();

    @Override
    File deleteById(Long id);

    File findByFileName(String fileName);
}
