package io.github.bolivaar16.radarbook.provider.dto;

import java.util.Set;

public record ProviderBookDTO(
    String title,
    String isbn,
    Set<String> authors,
    String publisher,
    Integer pages,
    Set<ProviderOfferDTO> offers
) {
}
