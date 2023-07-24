package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.GenreDao;
import com.ascendingdc.learnrestapi.repository.GenreRepository;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.entity.Genre;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("GenreDaoSpringDataJPAImpl")
public class GenreDaoSpringDataJPAImpl implements GenreDao {

    private Logger logger = (Logger) LoggerFactory.getLogger(GenreDaoSpringDataJPAImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        Genre savedGenre = genreRepository.save(genre);
        return savedGenre;
    }

    @Override
    public Genre update(Genre genre) {
        Genre updatedGenre = genreRepository.save(genre);
        return updatedGenre;
    }

    @Override
    public boolean deleteByName(String genreName) {
        return false;
    }

    @Override
    public boolean deleteById(Long genreId) {
        boolean successfulFlag = false;
        try{
            genreRepository.deleteById(genreId);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", genreId, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", genreId, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public boolean delete(Genre genre) {
        boolean successfulFlag = false;
        try{
            genreRepository.delete(genre);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", genre, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", genre, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) {
        Genre genre = null;
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if(genreOptional.isPresent())
            genre = genreOptional.get();
        return genre;
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
