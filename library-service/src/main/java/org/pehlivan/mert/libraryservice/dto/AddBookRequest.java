package org.pehlivan.mert.libraryservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookRequest {
    private String id;
    private String isbn;

}
