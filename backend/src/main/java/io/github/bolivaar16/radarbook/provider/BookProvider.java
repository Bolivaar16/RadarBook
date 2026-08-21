package io.github.bolivaar16.radarbook.provider;

import java.util.List;

import io.github.bolivaar16.radarbook.model.Book;

public interface BookProvider {
    List<Book> search(String query);
}
