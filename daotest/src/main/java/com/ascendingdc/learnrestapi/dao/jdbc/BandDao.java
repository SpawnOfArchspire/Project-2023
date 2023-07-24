package com.ascendingdc.learnrestapi.dao.jdbc;

import com.ascendingdc.learnrestapi.entity.Band;

import java.util.List;

public interface BandDao {
    Band save(Band band);
    Band update(Band band);
    boolean deleteByName(String bandName);
    boolean deleteById(Long bandId);
    boolean delete(Band band);
    List<Band> getBands();
    Band getBandById(Long id);
    Band getBandByName(String bandName);

    List<Band> getBandsWithCatalog();
    Band getBandAndAlbumsAndGenresByBandId(Long bandId);
    Band getBandAndAlbumsAndGenresByBandName(String bandName);

}
