package com.ascendingdc.learnrestapi.daoimpl.hibernate;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.util.HQLStatementUtil;
import com.ascendingdc.learnrestapi.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumDaoHibernateimpl implements AlbumDao {


    @Override
    public Album save(Album album, Long albumId) {
        return null;
    }

    @Override
    public Album update(Album album) {
        return null;
    }

    @Override
    public boolean deleteById(Long albumId) {
        return false;
    }

    @Override
    public boolean delete(Album album) {
        return false;
    }

    @Override
    public List<Album> getAlbums() {
        return null;
    }

    @Override
    public Album getAlbumById(Long id) {
        return null;
    }

    @Override
    public Album getAlbumByLoginName(String loginName) {
        return null;
    }

    @Override
    public List<Album> getAlbumsWithAssociatedGenres() {
        return null;
    }

    @Override
    public Album getAlbumWithAssociatedGenresByAlbumId(Long albumId) {
        return null;
    }

    @Override
    public Album getAlbumWithAssociatedGenresByLoginName(String loginName) {
        return null;
    }

    @Override
    public List<Album> getAlbumsWithAssociatedGenresByBandId(Long majorId) {
        return null;
    }
}
