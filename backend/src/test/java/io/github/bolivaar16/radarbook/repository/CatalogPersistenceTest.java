package io.github.bolivaar16.radarbook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import io.github.bolivaar16.radarbook.model.Author;
import io.github.bolivaar16.radarbook.model.Book;
import io.github.bolivaar16.radarbook.model.BookOffer;
import io.github.bolivaar16.radarbook.model.Platform;

@DataJpaTest
class CatalogPersistenceTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOfferRepository bookOfferRepository;

    @Test
    void repositoriesPersistAndReloadTheCompleteCatalogRelationshipGraph() {
        Author author = authorRepository.save(Author.builder().name("Ursula K. Le Guin").build());
        Book book = bookRepository.saveAndFlush(Book.builder()
                .title("A Wizard of Earthsea")
                .isbn("9780000000000")
                .authors(Set.of(author))
                .publisher("Parnassus")
                .pages(205)
                .build());
        Instant seenAt = Instant.parse("2026-08-20T10:15:30Z");
        BookOffer offer = bookOfferRepository.saveAndFlush(BookOffer.builder()
                .platform(Platform.SAN_PABLO)
                .url("https://example.test/books/earthsea")
                .price(new BigDecimal("19.95"))
                .availability(true)
                .availabilityDetails("In stock")
                .imageUrl("https://example.test/earthsea.jpg")
                .book(book)
                .lastSeenAt(seenAt)
                .build());

        bookRepository.flush();
        bookOfferRepository.flush();

        Book reloadedBook = bookRepository.findById(book.getId()).orElseThrow();
        BookOffer reloadedOffer = bookOfferRepository.findById(offer.getId()).orElseThrow();

        assertThat(reloadedBook.getAuthors()).extracting(Author::getName).containsExactly("Ursula K. Le Guin");
        assertThat(reloadedOffer.getBook().getId()).isEqualTo(book.getId());
        assertThat(reloadedOffer.getPlatform()).isEqualTo(Platform.SAN_PABLO);
        assertThat(reloadedOffer.getPrice()).isEqualByComparingTo("19.95");
        assertThat(reloadedOffer.getLastSeenAt()).isEqualTo(seenAt);
    }

    @Test
    void offerRequiresPlatformUrlAndLastSeenTimestamp() {
        assertThatThrownBy(() -> bookOfferRepository.saveAndFlush(BookOffer.builder()
                .url("https://example.test/missing-platform")
                .lastSeenAt(Instant.now())
                .build()))
                .isInstanceOfAny(DataIntegrityViolationException.class, org.hibernate.PropertyValueException.class);

        assertThatThrownBy(() -> bookOfferRepository.saveAndFlush(BookOffer.builder()
                .platform(Platform.EBAY)
                .lastSeenAt(Instant.now())
                .build()))
                .isInstanceOfAny(DataIntegrityViolationException.class, org.hibernate.PropertyValueException.class);

        assertThatThrownBy(() -> bookOfferRepository.saveAndFlush(BookOffer.builder()
                .platform(Platform.EBAY)
                .url("https://example.test/missing-timestamp")
                .build()))
                .isInstanceOfAny(DataIntegrityViolationException.class, org.hibernate.PropertyValueException.class);
    }
}
