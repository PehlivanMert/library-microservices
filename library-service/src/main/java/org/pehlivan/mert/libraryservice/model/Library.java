package org.pehlivan.mert.libraryservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Library {
    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //@ElementCollection annotasyonu, koleksiyon icerigini ayri bir tabloda depolar. Bu durumda, kullanicinin
    // kitap isimleri ayri bir tabloda saklanir ve bu tablo User tablosu ile iliskilendirilir.
    @ElementCollection
    private List<String> userBook = new ArrayList<>();
}
