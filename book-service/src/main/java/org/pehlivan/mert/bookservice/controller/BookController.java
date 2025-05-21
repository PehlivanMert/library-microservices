package org.pehlivan.mert.bookservice.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.pehlivan.mert.bookservice.dto.BookCreateDto;
import org.pehlivan.mert.bookservice.dto.BookDto;
import org.pehlivan.mert.bookservice.dto.BookIdDto;
import org.pehlivan.mert.bookservice.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable @NotEmpty String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(bookService.findBookDetailsById(id));
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody @Validated BookCreateDto dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }
}
