package io.github.bolivaar16.radarbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class BookOfferTest {

    @Test
    void builderCreatesOfferWithAllFields() {
        Book book = Book.builder().id(1L).build();
        BigDecimal price = new BigDecimal("19.95");

        BookOffer offer = BookOffer.builder()
                .id(3L)
                .platform(Platform.EBAY)
                .url("https://www.ebay.com/item/123")
                .price(price)
                .availability(true)
                .imageUrl("https://images.example.com/123.jpg")
                .book(book)
                .build();

        assertEquals(3L, offer.getId());
        assertEquals(Platform.EBAY, offer.getPlatform());
        assertEquals("https://www.ebay.com/item/123", offer.getUrl());
        assertEquals(price, offer.getPrice());
        assertTrue(offer.isAvailability());
        assertEquals("https://images.example.com/123.jpg", offer.getImageUrl());
        assertEquals(book, offer.getBook());
    }

    @Test
    void noArgsConstructorAndSettersAllowUpdatingOffer() {
        Book book = new Book();
        BookOffer offer = new BookOffer();

        assertNull(offer.getId());
        assertNull(offer.getPlatform());
        assertNull(offer.getPrice());
        assertFalse(offer.isAvailability());
        assertNull(offer.getBook());

        offer.setId(4L);
        offer.setPlatform(Platform.APIRAIN);
        offer.setUrl("https://api.example.com/book/4");
        offer.setPrice(new BigDecimal("8.50"));
        offer.setAvailability(true);
        offer.setImageUrl("https://images.example.com/4.jpg");
        offer.setBook(book);

        assertEquals(4L, offer.getId());
        assertEquals(Platform.APIRAIN, offer.getPlatform());
        assertEquals("https://api.example.com/book/4", offer.getUrl());
        assertEquals(new BigDecimal("8.50"), offer.getPrice());
        assertTrue(offer.isAvailability());
        assertEquals("https://images.example.com/4.jpg", offer.getImageUrl());
        assertEquals(book, offer.getBook());
    }
}
