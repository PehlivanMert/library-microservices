package org.pehlivan.mert.libraryservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {
    private String id;
    private List<BookDto> userBook = List.of();
}
