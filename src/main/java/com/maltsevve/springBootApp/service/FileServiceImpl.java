package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void save(File file) {
        log.info("IN FileServiceImpl save {}", file);
        fileRepository.save(file);
    }

    @Override
    public File getById(Long id) {
        log.info("IN FileServiceImpl getById {}", id);
        return fileRepository.getById(id);
    }

    @Override
    public List<File> getAll() {
        log.info("IN FileServiceImpl getAll");
        return fileRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("IN FileServiceImpl delete {}", id);
        fileRepository.deleteById(id);
    }

    @Override
    public File findByFileName(String fileName) {
        return null;
    }
}
