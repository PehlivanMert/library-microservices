package org.pehlivan.mert.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pehlivan.mert.bookservice.dto.BookCreateDto;
import org.pehlivan.mert.bookservice.dto.BookDto;
import org.pehlivan.mert.bookservice.dto.BookIdDto;
import org.pehlivan.mert.bookservice.exception.BookNotFoundException;
import org.pehlivan.mert.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::convert)
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + isbn));
    }

    public BookDto findBookDetailsById(String id) {
        return bookRepository.findById(id)
                .map(BookDto::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id:" + id));
    }

    public BookDto createBook(BookCreateDto dto) {
        return BookDto.convert(bookRepository.save(dto.toBook(dto)));
    }
}
