package org.pehlivan.mert.libraryservice.repository;

import org.pehlivan.mert.libraryservice.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
