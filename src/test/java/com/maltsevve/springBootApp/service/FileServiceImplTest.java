package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.File;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.FileRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceImplTest {
    @Mock
    private FileRepository fileRepository = Mockito.mock(FileRepository.class);

    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Mock
    private EventService eventService = Mockito.mock(EventService.class);

    @InjectMocks
    private final FileServiceImpl fileService = new FileServiceImpl(fileRepository, eventService, userRepository);

    @Test
    void findByFileName() {
        File file = getFile();

        when(fileRepository.findByFileName(file.getFileName())).thenReturn(file);

        File currentFile = fileService.findByFileName(file.getFileName());

        Assertions.assertEquals(1L, currentFile.getId());

        verify(fileRepository, times(1)).findByFileName(anyString());
    }

    @Test
    void save() { //TODO
    }

    @Test
    void getById() {
        File file = getFile();

        when(fileRepository.getById(1L)).thenReturn(file);

        fileService.getById(file.getId());

        Assertions.assertEquals(1L, file.getId());

        verify(fileRepository, times(1)).getById(1L);
    }

    @Test
    void getAll() {
        File file1 = getFile();
        File file2 = getFile();
        file2.setId(2L);

        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);

        when(fileRepository.findAll()).thenReturn(files);

        List<File> result = fileService.getAll();

        Assertions.assertEquals(files.size(), result.size());

        verify(fileRepository, times(1)).findAll();
    }

//    @Test
//    void deleteById() { TODO
//        File file = getFile();
//
//        when(fileRepository.getById(1L)).thenReturn(file);
//        when(userRepository.findByUsername("User")).thenReturn(getUser());
//
//        file = fileService.deleteById(file.getId());
//
//        Assertions.assertEquals(Status.DELETED, file.getStatus());
//
//        verify(fileRepository, times(1)).save(file);
//    }

    private File getFile() {
        File file = new File();
        file.setId(1L);
        file.setFileName("File");
        file.setFileUrl("http://api/v1/files/file");
        file.setCreated(new Date());
        file.setUpdated(new Date());
        file.setStatus(Status.ACTIVE);

        return file;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("User");

        return user;
    }
}