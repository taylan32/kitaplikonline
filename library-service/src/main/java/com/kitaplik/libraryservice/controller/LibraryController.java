package com.kitaplik.libraryservice.controller;

import com.kitaplik.libraryservice.dto.AddBookRequest;
import com.kitaplik.libraryservice.dto.LibraryDto;
import com.kitaplik.libraryservice.service.LibraryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private final Logger logger = LoggerFactory.getLogger(LibraryController.class);
    private final LibraryService libraryService;
    private final Environment environment;

    public LibraryController(LibraryService libraryService, Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable String id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library was created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping("/")
    public ResponseEntity<Void> addBookToLibrary(@RequestBody @Valid AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<String>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }
}
