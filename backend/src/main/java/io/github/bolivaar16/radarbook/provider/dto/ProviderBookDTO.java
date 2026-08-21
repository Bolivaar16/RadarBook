package io.github.bolivaar16.radarbook.provider.dto;

import java.math.BigDecimal;
import java.util.Set;

public record DTOBookProvider(
    String title,
    String isbn,
    Set<String> authors,
    String publisher,
    int pages,
    BigDecimal price,
    String url
) {
}
