package io.github.bolivaar16.radarbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;

class BookModelTest {

    @Test
    void builderRetainsTheCompleteBookAggregate() {
        Author author = Author.builder().id(2L).name("Ursula K. Le Guin").build();
        BookOffer offer = BookOffer.builder()
                .id(3L)
                .platform(Platform.SAN_PABLO)
                .url("https://example.test/books/earthsea")
                .price(new BigDecimal("19.95"))
                .availability(true)
                .availabilityDetails("In stock")
                .imageUrl("https://example.test/earthsea.jpg")
                .lastSeenAt(Instant.parse("2026-08-20T10:15:30Z"))
                .build();

        Book book = Book.builder()
                .id(1L)
                .title("A Wizard of Earthsea")
                .isbn("9780000000000")
                .authors(Set.of(author))
                .publisher("Parnassus")
                .pages(205)
                .offers(Set.of(offer))
                .build();
        offer.setBook(book);

        assertEquals(1L, book.getId());
        assertEquals("A Wizard of Earthsea", book.getTitle());
        assertEquals("9780000000000", book.getIsbn());
        assertEquals(Set.of(author), book.getAuthors());
        assertEquals("Parnassus", book.getPublisher());
        assertEquals(205, book.getPages());
        assertEquals(Set.of(offer), book.getOffers());
        assertEquals(book, offer.getBook());
        assertEquals(Platform.SAN_PABLO, offer.getPlatform());
        assertEquals(new BigDecimal("19.95"), offer.getPrice());
        assertEquals("In stock", offer.getAvailabilityDetails());
        assertEquals(Instant.parse("2026-08-20T10:15:30Z"), offer.getLastSeenAt());
    }

    @Test
    void defaultsAndSettersSupportIncrementalEntityConstruction() {
        Book book = new Book();
        Author author = new Author();
        BookOffer offer = new BookOffer();

        assertNotNull(book.getAuthors());
        assertTrue(book.getAuthors().isEmpty());
        assertNull(book.getOffers());
        assertNotNull(author.getBooks());
        assertTrue(author.getBooks().isEmpty());
        assertFalse(offer.isAvailability());
        assertNull(offer.getLastSeenAt());

        book.setTitle("The Dispossessed");
        book.setAuthors(Set.of(author));
        book.setOffers(Set.of(offer));
        author.setName("Ursula K. Le Guin");
        offer.setAvailability(true);
        offer.setBook(book);

        assertEquals("The Dispossessed", book.getTitle());
        assertEquals(Set.of(author), book.getAuthors());
        assertEquals(Set.of(offer), book.getOffers());
        assertEquals("Ursula K. Le Guin", author.getName());
        assertTrue(offer.isAvailability());
        assertEquals(book, offer.getBook());
    }
}
