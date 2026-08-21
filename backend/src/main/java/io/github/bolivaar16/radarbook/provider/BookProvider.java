package io.github.bolivaar16.radarbook.provider;

import java.util.List;

import io.github.bolivaar16.radarbook.provider.dto.ProviderBookDTO;

public interface BookProvider {
    List<ProviderBookDTO> search(String query);
}
