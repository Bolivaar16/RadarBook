package io.github.bolivaar16.radarbook.provider.dto;

import java.math.BigDecimal;

import io.github.bolivaar16.radarbook.model.Platform;

public record ProviderOfferDTO(
    BigDecimal price,
    String url,
    boolean availability,
    String availabilityDetails,
    String imageUrl,
    Platform platform
) {
}
