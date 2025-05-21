package org.pehlivan.mert.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pehlivan.mert.libraryservice.client.BookServiceClient;
import org.pehlivan.mert.libraryservice.dto.AddBookRequest;
import org.pehlivan.mert.libraryservice.dto.LibraryDto;
import org.pehlivan.mert.libraryservice.exception.LibraryNotFoundException;
import org.pehlivan.mert.libraryservice.model.Library;
import org.pehlivan.mert.libraryservice.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(book -> bookServiceClient.getBookById(book).getBody())
                        .collect(Collectors.toList()));
        return libraryDto;
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return LibraryDto.builder()
                .id(newLibrary.getId())
                .build();
    }

    public void addBookToLibrary(AddBookRequest request) {
        String bookId = bookServiceClient.getBookByIsbn(request.getIsbn()).getBody().getBookId();

        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + request.getId()));

        library.getUserBook()
                .add(bookId);

        libraryRepository.save(library);
    }

    public List<String> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(l -> l.getId())
                .collect(Collectors.toList());
    }
}
