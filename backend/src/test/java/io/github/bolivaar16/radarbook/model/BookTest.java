package io.github.bolivaar16.radarbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void builderCreatesBookWithAllFields() {
        BookOffer offer = BookOffer.builder().id(8L).build();
        List<String> authors = List.of("Ursula K. Le Guin", "Another author");

        Book book = Book.builder()
                .id(1L)
                .title("A Wizard of Earthsea")
                .isbn("978-0-00-000000-0")
                .authors(authors)
                .publisher("Parnassus")
                .pages(205)
                .offer(offer)
                .build();

        assertEquals(1L, book.getId());
        assertEquals("A Wizard of Earthsea", book.getTitle());
        assertEquals("978-0-00-000000-0", book.getIsbn());
        assertEquals(authors, book.getAuthors());
        assertEquals("Parnassus", book.getPublisher());
        assertEquals(205, book.getPages());
        assertEquals(offer, book.getOffer());
    }

    @Test
    void noArgsConstructorAndSettersAllowUpdatingBook() {
        BookOffer offer = new BookOffer();
        Book book = new Book();

        assertNull(book.getId());
        assertNull(book.getTitle());
        assertEquals(0, book.getPages());
        assertNull(book.getOffer());

        book.setId(2L);
        book.setTitle("The Dispossessed");
        book.setIsbn("978-0-06-105488-4");
        book.setAuthors(List.of("Ursula K. Le Guin"));
        book.setPublisher("Harper & Row");
        book.setPages(387);
        book.setOffer(offer);

        assertEquals(2L, book.getId());
        assertEquals("The Dispossessed", book.getTitle());
        assertEquals("978-0-06-105488-4", book.getIsbn());
        assertEquals(List.of("Ursula K. Le Guin"), book.getAuthors());
        assertEquals("Harper & Row", book.getPublisher());
        assertEquals(387, book.getPages());
        assertEquals(offer, book.getOffer());
    }
}
