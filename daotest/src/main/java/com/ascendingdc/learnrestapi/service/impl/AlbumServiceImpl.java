package com.ascendingdc.learnrestapi.service.impl;


import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.dto.AlbumDto;
import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.entity.Genre;
import com.ascendingdc.learnrestapi.service.AlbumService;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class AlbumServiceImpl implements AlbumService {

    private Logger logger = (Logger) LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Autowired
    @Qualifier("AlbumSpringDataJPADao")
    private AlbumDao albumDao;


    @Override
    public AlbumDto save(AlbumDto albumDto, Long albumId) {
        Album album = albumDto.convertAlbumDtoToAlbum();
        Album savedAlbum = albumDao.save(album, albumId);
        AlbumDto savedAlbumDto = savedAlbum.convertAlbumToAlbumDto();
        return savedAlbumDto;
    }

    @Override
    public AlbumDto update(AlbumDto albumDto) {
        Album album = albumDto.convertAlbumDtoToAlbum();
        Album updatedAlbum = albumDao.update(album);
        AlbumDto updatedAlbumDto = updatedAlbum.convertAlbumToAlbumDto();
        return updatedAlbumDto;
    }

    @Override
    public boolean deleteById(Long albumId) {
        boolean deleteResult = albumDao.deleteById(albumId);
        logger.debug(" within method AlbumService.deleteById(), albumId={}}, deleteResult={}");
        return deleteResult;
    }

    @Override
    public boolean delete(AlbumDto albumDto) {
        Album album = albumDto.convertAlbumDtoToAlbum();
        boolean deleteResult = albumDao.delete(album);
        return deleteResult;
    }

    @Override
    public List<AlbumDto> getAlbums() {
        List<Album> albumList = albumDao.getAlbums();
        List<AlbumDto> albumDtoList = getAlbumDtoListFromAlbumList(albumList);
        return albumDtoList;
    }

    private List<AlbumDto> getAlbumDtoListFromAlbumList(List<Album> albumList) {
        List<AlbumDto> albumDtoList = new ArrayList<>();
        for(Album album : albumList){
            AlbumDto albumDto = album.convertAlbumToAlbumDto();
            albumDtoList.add(albumDto);
        }
        return albumDtoList;
    }

    @Override
    public AlbumDto getAlbumById(Long id) {
        Album retrievedAlbum = albumDao.getAlbumById(id);
        AlbumDto retrievedAlbumDto = retrievedAlbum.convertAlbumToAlbumDto();
        return retrievedAlbumDto;
    }

    @Override
    public AlbumDto getAlbumByLoginName(String loginName) {
        Album retrievedAlbum = albumDao.getAlbumByLoginName(loginName);
        AlbumDto retrievedAlbumDto = retrievedAlbum.convertAlbumToAlbumDto();
        return retrievedAlbumDto;
    }

    @Override
    public List<AlbumDto> getAlbumsWithAssociatedGenres() {

        return null;
    }

    @Override
    public AlbumDto getAlbumWithAssociatedGenresByAlbumId(Long albumId) {
        Album retrievedAlbum = albumDao.getAlbumWithAssociatedGenresByAlbumId(albumId);
        AlbumDto retrievedAlbumDto = retrievedAlbum.convertAlbumToAlbumDto();
        return retrievedAlbumDto;
    }

    @Override
    public AlbumDto getAlbumWithAssociatedGenresByLoginName(String loginName) {
        Album retrievedAlbum = albumDao.getAlbumWithAssociatedGenresByLoginName(loginName);
        AlbumDto retrievedAlbumDto = retrievedAlbum.convertAlbumToAlbumDto();
        return retrievedAlbumDto;
    }

    @Override
    public List<AlbumDto> getAlbumsWithAssociatedGenresByBandId(Long majorId) {
        return null;
    }
}
