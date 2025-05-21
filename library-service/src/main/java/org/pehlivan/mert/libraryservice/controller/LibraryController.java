package org.pehlivan.mert.libraryservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pehlivan.mert.libraryservice.dto.AddBookRequest;
import org.pehlivan.mert.libraryservice.dto.LibraryDto;
import org.pehlivan.mert.libraryservice.service.LibraryService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;
    private final Environment environment;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable String id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        //log.info("Library created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }
}
