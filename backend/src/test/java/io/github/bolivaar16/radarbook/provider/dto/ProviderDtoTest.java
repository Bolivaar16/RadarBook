package io.github.bolivaar16.radarbook.provider.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.bolivaar16.radarbook.model.Platform;

class ProviderDtoTest {

    @Test
    void offerDtoExposesItsProviderContractAndRecordValueSemantics() {
        ProviderOfferDTO offer = new ProviderOfferDTO(new BigDecimal("12.50"), "https://example.test/1", true,
                "Available", "https://example.test/1.jpg", Platform.EBAY);

        assertEquals(new BigDecimal("12.50"), offer.price());
        assertEquals("https://example.test/1", offer.url());
        assertEquals("Available", offer.availabilityDetails());
        assertEquals(Platform.EBAY, offer.platform());
        assertEquals(offer, new ProviderOfferDTO(new BigDecimal("12.50"), "https://example.test/1", true,
                "Available", "https://example.test/1.jpg", Platform.EBAY));
        assertNotEquals(offer, new ProviderOfferDTO(new BigDecimal("12.50"), "https://example.test/1", false,
                "Available", "https://example.test/1.jpg", Platform.EBAY));
    }

    @Test
    void bookDtoKeepsAllProviderDataIncludingMultipleOffers() {
        ProviderOfferDTO first = new ProviderOfferDTO(new BigDecimal("10.00"), "https://example.test/1", true,
                null, null, Platform.APIRAIN);
        ProviderOfferDTO second = new ProviderOfferDTO(new BigDecimal("11.00"), "https://example.test/2", false,
                "Out of stock", null, Platform.SAN_PABLO);
        ProviderBookDTO book = new ProviderBookDTO("Book", "9780000000000", Set.of("Author A", "Author B"),
                "Publisher", 320, Set.of(first, second));

        assertEquals("Book", book.title());
        assertEquals(Set.of("Author A", "Author B"), book.authors());
        assertEquals(320, book.pages());
        assertEquals(Set.of(first, second), book.offers());
    }
}
