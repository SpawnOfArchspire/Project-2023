package com.ascendingdc.learnrestapi.service.impl;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.dao.jdbc.BandDao;
import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.exception.BandNotFoundException;
import com.ascendingdc.learnrestapi.service.BandService;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BandServiceImpl implements BandService {

    private Logger logger = (Logger) LoggerFactory.getLogger(BandServiceImpl.class);

    @Autowired
    @Qualifier("BandSpringDataJPADao")
    private BandDao bandDao;


    @Override
    public BandDto save(BandDto bandDto) {
        Band band = bandDto.convertBandDtoToBand();
        Band savedBand = bandDao.save(band);
        BandDto savedBandDto = savedBand.convertBandToBandDto();
        return savedBandDto;
    }

    @Override
    public BandDto update(BandDto bandDto) {
        Band band = bandDto.convertBandDtoToBand();
        Band updatedBand = bandDao.update(band);
        BandDto updatedBandDto = updatedBand.convertBandToBandDto();
        return updatedBandDto;
    }

    @Override
    public boolean deleteByName(String bandName) {
        return bandDao.deleteByName(bandName);
    }

    @Override
    public boolean deleteById(Long bandId) {
        boolean deleteResult = bandDao.deleteById(bandId);
        logger.debug(" within method BandService.deleteById(), bandId={}}, deleteResult={}");
        return deleteResult;
    }

    @Override
    public boolean delete(BandDto bandDto) {
        Band band = bandDto.convertBandDtoToBand();
        boolean deleteResult = bandDao.delete(band);
        return deleteResult;
    }

    @Override
    public List<BandDto> getBands() {
        List<Band> bandList = bandDao.getBands();
        List<BandDto> bandDtoList = getBandDtoListFromBandList(bandList);
        return bandDtoList;
    }

    private List<BandDto> getBandDtoListFromBandList(List<Band> bandList) {
        List<BandDto> bandDtoList = new ArrayList<>();
        for(Band band : bandList){
            BandDto bandDto = band.convertBandToBandDto();
            bandDtoList.add(bandDto);
        }
        return bandDtoList;
    }

    @Override
    public BandDto getBandById(Long id) {
        BandDto bandDto = null;
        Band band = bandDao.getBandById(id);
        if(band != null){
            bandDto = band.convertBandToBandDto();
        } else{
            throw new BandNotFoundException("Could not find band with id = " + id);
        }
        return bandDto;
    }

    @Override
    public BandDto getBandByName(String bandName) {
        Band retrievedBand = bandDao.getBandByName(bandName);
        BandDto retrievedBandDto = retrievedBand.convertBandToBandDto();
        return retrievedBandDto;
    }

    @Override
    public List<BandDto> getBandsWithCatalog() {
        List<Band> bandList = bandDao.getBandsWithCatalog();
        List<BandDto> bandDtoList = getBandDtoListFromBandList(bandList);
        return bandDtoList;
    }

    @Override
    public BandDto getBandAndAlbumsAndGenresByBandId(Long bandId) {
        Band retrievedBand = bandDao.getBandAndAlbumsAndGenresByBandId(bandId);
        BandDto retrievedBandDto = retrievedBand.convertBandToBandDto();
        return retrievedBandDto;
    }

    @Override
    public BandDto getBandAndAlbumsAndGenresByBandName(String bandName) {
        Band retrievedBand = bandDao.getBandAndAlbumsAndGenresByBandName(bandName);
        BandDto retrievedBandDto = retrievedBand.convertBandToBandDto();
        return retrievedBandDto;
    }
}
