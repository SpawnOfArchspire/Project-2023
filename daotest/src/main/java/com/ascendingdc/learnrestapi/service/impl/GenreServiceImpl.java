package com.ascendingdc.learnrestapi.service.impl;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.dao.jdbc.GenreDao;
import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.dto.GenreDto;

import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.entity.Genre;
import com.ascendingdc.learnrestapi.service.GenreService;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    private Logger logger = (Logger) LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    @Qualifier("GenreSpringDataJPADao")
    private GenreDao genreDao;


    @Override
    public GenreDto save(GenreDto genreDto) {
        Genre genre = genreDto.convertGenreDtoToGenre();
        Genre savedGenre = genreDao.save(genre);
        GenreDto savedGenreDto = savedGenre.convertGenreToGenreDto();
        return savedGenreDto;
    }

    @Override
    public GenreDto update(GenreDto genreDto) {
        Genre genre = genreDto.convertGenreDtoToGenre();
        Genre updatedGenre = genreDao.update(genre);
        GenreDto updatedGenreDto = updatedGenre.convertGenreToGenreDto();
        return updatedGenreDto;
    }

    @Override
    public boolean deleteByName(String genreName) {
        return genreDao.deleteByName(genreName);
    }

    @Override
    public boolean deleteById(Long genreId) {
        boolean deleteResult = genreDao.deleteById(genreId);
        logger.debug(" within method GenreService.deleteById(), genreId={}}, deleteResult={}");
        return deleteResult;
    }

    @Override
    public boolean delete(GenreDto genreDto) {
        Genre genre = genreDto.convertGenreDtoToGenre();
        boolean deleteResult = genreDao.delete(genre);
        return deleteResult;
    }

    @Override
    public List<GenreDto> getGenres() {
        List<Genre> genreList = genreDao.getGenres();
        List<GenreDto> genreDtoList = getGenreDtoListFromGenreList(genreList);
        return genreDtoList;
    }

    private List<GenreDto> getGenreDtoListFromGenreList(List<Genre> genreList) {
        List<GenreDto> genreDtoList = new ArrayList<>();
        for(Genre genre : genreList){
            GenreDto genreDto = genre.convertGenreToGenreDto();
            genreDtoList.add(genreDto);
        }
        return genreDtoList;
    }

    @Override
    public GenreDto getGenreById(Long id) {
        Genre retrievedGenre = genreDao.getGenreById(id);
        GenreDto retrievedGenreDto = retrievedGenre.convertGenreToGenreDto();
        return retrievedGenreDto;
    }

    @Override
    public GenreDto getGenreByName(String genreName) {
        Genre retrievedGenre = genreDao.getGenreByName(genreName);
        GenreDto retrievedGenreDto = retrievedGenre.convertGenreToGenreDto();
        return retrievedGenreDto;
    }

    @Override
    public List<GenreDto> getGenresWithAssociatedAlbums() {
        List<Genre> genreList = genreDao.getGenresWithAssociatedAlbums();
        List<GenreDto> genreDtoList = getGenreDtoListFromGenreList(genreList);
        return genreDtoList; //temporary
    }

    @Override
    public GenreDto getGenreWithAssociatedAlbumsById(Long genreId) {

        Genre retrievedGenre = genreDao.getGenreWithAssociatedAlbumsById(genreId);
        GenreDto retrievedGenreDto = retrievedGenre.convertGenreToGenreDto();
        return retrievedGenreDto;
    }

    @Override
    public GenreDto getGenreWithAssociatedAlbumsByName(String genreName) {
        Genre retrievedGenre = genreDao.getGenreWithAssociatedAlbumsByName(genreName);
        GenreDto retrievedGenreDto = retrievedGenre.convertGenreToGenreDto();
        return retrievedGenreDto;
    }
}
