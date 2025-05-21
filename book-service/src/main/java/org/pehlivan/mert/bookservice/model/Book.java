package org.pehlivan.mert.bookservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Book year is required")
    @Past(message = "Book year must be in the past")
    private int bookYear;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Press name is required")
    private String pressName;

    @NotBlank(message = "ISBN is required")
    @Column(unique = true)
    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private boolean available;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        available = true;
        status = BookStatus.AVAILABLE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

@Getter
enum BookStatus {
    AVAILABLE("Available"),
    BORROWED("Borrowed"),
    RESERVED("Reserved"),
    MAINTENANCE("Under Maintenance");

    private final String displayName;

    BookStatus(String displayName) {
        this.displayName = displayName;
    }
}
