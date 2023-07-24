package com.ascendingdc.learnrestapi.dao.jdbc;

import com.ascendingdc.learnrestapi.entity.Genre;

import java.util.List;

public interface GenreDao {
    Genre save(Genre genre);
    Genre update(Genre genre);
    boolean deleteByName(String genreName);
    boolean deleteById(Long genreId);
    boolean delete(Genre genre);
    List<Genre> getGenres();
    Genre getGenreById(Long id);
    Genre getGenreByName(String genreName);
    List<Genre> getGenresWithAssociatedAlbums();
    Genre getGenreWithAssociatedAlbumsById(Long genreId);
    Genre getGenreWithAssociatedAlbumsByName(String genreName);
}