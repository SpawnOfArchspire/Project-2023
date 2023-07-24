package com.ascendingdc.learnrestapi.service;

import com.ascendingdc.learnrestapi.dto.AlbumDto;
import com.ascendingdc.learnrestapi.entity.Album;

import java.util.List;

public interface AlbumService {
    AlbumDto save(AlbumDto albumDto, Long albumId);

    AlbumDto update(AlbumDto albumDto);

    boolean deleteById(Long albumId);

    boolean delete(AlbumDto albumDto);

    List<AlbumDto> getAlbums();

    AlbumDto getAlbumById(Long id);

    AlbumDto getAlbumByLoginName(String loginName);


    List<AlbumDto> getAlbumsWithAssociatedGenres();

    AlbumDto getAlbumWithAssociatedGenresByAlbumId(Long albumId);

    AlbumDto getAlbumWithAssociatedGenresByLoginName(String loginName);

    List<AlbumDto> getAlbumsWithAssociatedGenresByBandId(Long majorId);
}
