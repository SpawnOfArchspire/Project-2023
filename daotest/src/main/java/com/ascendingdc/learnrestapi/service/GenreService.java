package com.ascendingdc.learnrestapi.service;

import com.ascendingdc.learnrestapi.dto.GenreDto;
import com.ascendingdc.learnrestapi.entity.Genre;

import java.util.List;

public interface GenreService {
    GenreDto save(GenreDto genreDto);
    GenreDto update(GenreDto genreDto);
    boolean deleteByName(String genreName);
    boolean deleteById(Long genreId);
    boolean delete(GenreDto genreDto);
    List<GenreDto> getGenres();
    GenreDto getGenreById(Long id);
    GenreDto getGenreByName(String genreName);
    List<GenreDto> getGenresWithAssociatedAlbums();
    GenreDto getGenreWithAssociatedAlbumsById(Long genreId);
    GenreDto getGenreWithAssociatedAlbumsByName(String genreName);
}
