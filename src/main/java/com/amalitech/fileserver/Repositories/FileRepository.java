package com.amalitech.fileserver.Repositories;

import com.amalitech.fileserver.Models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByName(String fileName);
}
