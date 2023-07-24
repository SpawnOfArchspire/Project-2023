package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.BandDao;
import com.ascendingdc.learnrestapi.repository.BandRepository;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.entity.Band;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("BandDaoSpringDataJPAImpl")
public class BandDaoSpringDataJPAImpl implements BandDao {

    private Logger logger = (Logger) LoggerFactory.getLogger(BandDaoSpringDataJPAImpl.class);

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Band save(Band band) {
        Band savedBand = bandRepository.save(band);
        return savedBand;
    }

    @Override
    public Band update(Band band) {
        Band updatedBand = bandRepository.save(band);
        return updatedBand;
    }

    @Override
    public boolean deleteByName(String bandName) {
        return false;
    }

    @Override
    public boolean deleteById(Long bandId) {
        boolean successfulFlag = false;
        try{
            bandRepository.deleteById(bandId);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", bandId, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", bandId, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public boolean delete(Band band) {
        boolean successfulFlag = false;
        try{
            bandRepository.delete(band);
            successfulFlag = true;
        } catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with albumID={}, error={}", band, iae.getMessage());
        } catch(OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with albumID={}, error={}", band, olfe.getMessage());
        }
        return successfulFlag;
    }

    @Override
    public List<Band> getBands() {
        return bandRepository.findAll();
    }

    @Override
    public Band getBandById(Long id) {
        Band band = null;
        Optional<Band> bandOptional = bandRepository.findById(id);
        if(bandOptional.isPresent())
            band = bandOptional.get();
        return band;
    }

    @Override
    public Band getBandByName(String bandName) {

        return null;
    }

    @Override
    public List<Band> getBandsWithCatalog() {
        return null;
    }

    @Override
    public Band getBandAndAlbumsAndGenresByBandId(Long bandId) {
        return null;
    }

    @Override
    public Band getBandAndAlbumsAndGenresByBandName(String bandName) {
        return null;
    }
}