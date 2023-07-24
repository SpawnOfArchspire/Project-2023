package com.ascendingdc.learnrestapi.service;

import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.entity.Band;

import java.util.List;

public interface BandService {
    BandDto save(BandDto bandDto);
    BandDto update(BandDto bandDto);
    boolean deleteByName(String bandName);
    boolean deleteById(Long bandId);
    boolean delete(BandDto bandDto);
    List<BandDto> getBands();
    BandDto getBandById(Long id);
    BandDto getBandByName(String bandName);

    List<BandDto> getBandsWithCatalog();
    BandDto getBandAndAlbumsAndGenresByBandId(Long bandId);
    BandDto getBandAndAlbumsAndGenresByBandName(String bandName);
}
