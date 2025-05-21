package org.pehlivan.mert.libraryservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.pehlivan.mert.libraryservice.dto.BookDto;
import org.pehlivan.mert.libraryservice.dto.BookIdDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "book-service", path = "/v1/books")
public interface BookServiceClient {

    Logger log = LoggerFactory.getLogger(BookServiceClient.class);

    // *** @CircuitBreaker anatasyonu ile hata aldigimizda fallback methodu calistiriyoruz.
    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception) {
        log.info("Book not found by isbn " + isbn + ", returning default BookDto object");
        return ResponseEntity.ok(new BookIdDto("default-book", "default-isbn"));
    }

    @GetMapping("/book/{bookId}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getBookById(@PathVariable(value = "bookId") String bookId);

    default ResponseEntity<BookDto> getBookByIdFallback(String bookId, Exception exception) {
        log.info("Book not found by id " + bookId + ", returning default BookDto object");

        BookDto bookDto = BookDto.builder()
                .id(BookIdDto.builder().bookId("default-book").isbn("default-isbn").build())
                .title("Sample Title")
                .bookYear(2023)
                .author("Sample Author")
                .pressName("Sample Press")
                .build();

        return ResponseEntity.ok(bookDto);
    }
}