package com.ascendingdc.learnrestapi.daoimpl.hibernate;

import com.ascendingdc.learnrestapi.dao.jdbc.GenreDao;
import com.ascendingdc.learnrestapi.entity.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoHibernateimpl implements GenreDao {
    @Override
    public Genre save(Genre genre) {
        return null;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }

    @Override
    public boolean deleteByName(String genreName) {
        return false;
    }

    @Override
    public boolean deleteById(Long genreId) {
        return false;
    }

    @Override
    public boolean delete(Genre genre) {
        return false;
    }

    @Override
    public List<Genre> getGenres() {
        return null;
    }

    @Override
    public Genre getGenreById(Long id) {
        return null;
    }

    @Override
    public Genre getGenreByName(String genreName) {
        return null;
    }

    @Override
    public List<Genre> getGenresWithAssociatedAlbums() {
        return null;
    }

    @Override
    public Genre getGenreWithAssociatedAlbumsById(Long genreId) {
        return null;
    }

    @Override
    public Genre getGenreWithAssociatedAlbumsByName(String genreName) {
        return null;
    }
}
