package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.FileRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final EventService eventService;
    private final UserRepository userRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, EventService eventService, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.eventService = eventService;
        this.userRepository = userRepository;
    }

    @Override
    public File findByFileName(String fileName) {
        File file = fileRepository.findByFileName(fileName);
        log.info("IN findByFileName - file: {} found by file name: {}", file, fileName);
        return file;
    }

    @Override
    public void save(File file) {
        if (file.getCreated() == null) {
            file.setCreated(new Date());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            file.setUpdated(new Date());
            file.setStatus(Status.ACTIVE);
            fileRepository.save(file);

            String username = authentication.getName();
            User user = userRepository.findByUsername(username);

            Event event = new Event();
            event.setFile(file);
            event.setUser(user);
            eventService.save(event);

            log.info("IN FileServiceImpl save {}", file);
        }
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
        File file = getById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            file.setStatus(Status.DELETED);
            file.setUpdated(new Date());
            fileRepository.save(file);

            String username = authentication.getName();
            User user = userRepository.findByUsername(username);

            Event event = new Event();
            event.setFile(file);
            event.setUser(user);
            eventService.save(event);

            log.info("IN FileServiceImpl delete {}", id);
        }
//        fileRepository.deleteById(id);
    }
}
