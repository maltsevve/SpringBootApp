package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Event;
import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.FileRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import com.maltsevve.springBootApp.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final EventService eventService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public File findByFileName(String fileName) {
        File file = fileRepository.findByFileName(fileName);
        log.info("IN findByFileName - file: {} found by file name: {}", file, fileName);
        return file;
    }

    @Override
    public File save(File file, String token) {
        if (file.getCreated() == null) {
            file.setCreated(new Date());
        }

        file.setUpdated(new Date());
        file.setStatus(Status.ACTIVE);
        File savedFile = fileRepository.save(file);

        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.findByUsername(username);

        Event event = new Event();
        event.setFile(file);
        event.setUser(user);
        eventService.save(event);

        log.info("IN FileServiceImpl save {}", file);

        return savedFile;
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
    public File deleteById(Long id, String token) {
        File file = getById(id);

        file.setStatus(Status.DELETED);
        file.setUpdated(new Date());
        fileRepository.save(file);

        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.findByUsername(username);

        Event event = new Event();
        event.setFile(file);
        event.setUser(user);
        eventService.save(event);

        log.info("IN FileServiceImpl delete {}", id);

//        fileRepository.deleteById(id);
        return file;
    }
}
