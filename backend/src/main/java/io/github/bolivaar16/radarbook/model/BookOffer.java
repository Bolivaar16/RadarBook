package io.github.bolivaar16.radarbook.model;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String url;
    private BigDecimal price;
    private boolean availability;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
}
