package com.maltsevve.springBootApp.repository;

import com.maltsevve.springBootApp.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByFileName(String filename);
}
