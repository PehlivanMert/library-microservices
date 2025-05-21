package org.pehlivan.mert.bookservice;

import org.pehlivan.mert.bookservice.model.Book;
import org.pehlivan.mert.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = Book.builder()
                .title("Book 1")
                .bookYear(2023)
                .author("Author 1")
                .pressName("Press 1")
                .isbn("978-1111111111")
                .build();

        Book book2 = Book.builder()
                .title("Book 2")
                .bookYear(2023)
                .author("Author 2")
                .pressName("Press 2")
                .isbn("980-2222222222")
                .build();

        Book book3 = Book.builder()
                .title("Book 3")
                .bookYear(2023)
                .author("Author 3")
                .pressName("Press 3")
                .isbn("988-3333333333")
                .build();

        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
    }
}
