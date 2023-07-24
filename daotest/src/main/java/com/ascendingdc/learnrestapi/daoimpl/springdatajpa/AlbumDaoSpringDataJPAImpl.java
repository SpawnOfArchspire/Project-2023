package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.daoimpl.hibernate.BandDaoHibernateimpl;
import com.ascendingdc.learnrestapi.repository.AlbumRepository;
import com.ascendingdc.learnrestapi.entity.Album;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("AlbumDaoSpringDataJPAImpl")
public class AlbumDaoSpringDataJPAImpl implements AlbumDao {

    private Logger logger = (Logger) LoggerFactory.getLogger(AlbumDaoSpringDataJPAImpl.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album save(Album album, Long albumId) {
        Album savedAlbum = albumRepository.save(album);
        return savedAlbum;
    }

    @Override
    public Album update(Album album) {
        Album updatedAlbum = albumRepository.save(album);
        return updatedAlbum;
    }

    @Override
    public boolean deleteById(Long albumId) {
        boolean successfulFlag = false;
        try{
            albumRepository.deleteById(albumId);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", albumId, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", albumId, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public boolean delete(Album album) {
        boolean successfulFlag = false;
        try{
            albumRepository.delete(album);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", album, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", album, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbumById(Long id) {
        Album album = null;
        Optional<Album> albumOptional = albumRepository.findById(id);
        if(albumOptional.isPresent())
            album = albumOptional.get();
        return album;
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
