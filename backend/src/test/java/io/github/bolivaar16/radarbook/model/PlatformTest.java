package io.github.bolivaar16.radarbook.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PlatformTest {

    @Test
    void containsExpectedPlatformsInDeclarationOrder() {
        assertArrayEquals(
                new Platform[] { Platform.EBAY, Platform.APIRAIN, Platform.SAN_PABLO },
                Platform.values());
    }

    @Test
    void valueOfReturnsMatchingPlatformAndRejectsUnknownValue() {
        assertEquals(Platform.SAN_PABLO, Platform.valueOf("SAN_PABLO"));
        assertThrows(IllegalArgumentException.class, () -> Platform.valueOf("AMAZON"));
    }
}
