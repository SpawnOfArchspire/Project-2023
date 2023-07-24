package com.ascendingdc.learnrestapi.repository;

import com.ascendingdc.learnrestapi.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    @Query("SELECT distinct b FROM Band as b left join fetch b.albums as album left join fetch album.genres where b.id = :id")
    Band findBandWithWItchAlbumsAndGenresByBandId(@Param(value = "id")Long bandId);
}
