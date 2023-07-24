package com.ascendingdc.learnrestapi.dao.jdbc;

import com.ascendingdc.learnrestapi.entity.Album;
import jakarta.transaction.SystemException;


import java.util.List;
import java.util.logging.Logger;

public interface AlbumDao {

    Album save(Album album, Long albumId);

    Album update(Album album);

    boolean deleteById(Long albumId);

    boolean delete(Album album);

    List<Album> getAlbums();

    Album getAlbumById(Long id);

    Album getAlbumByLoginName(String loginName);


    List<Album> getAlbumsWithAssociatedGenres();

    Album getAlbumWithAssociatedGenresByAlbumId(Long albumId);

    Album getAlbumWithAssociatedGenresByLoginName(String loginName);

    List<Album> getAlbumsWithAssociatedGenresByBandId(Long majorId);
}