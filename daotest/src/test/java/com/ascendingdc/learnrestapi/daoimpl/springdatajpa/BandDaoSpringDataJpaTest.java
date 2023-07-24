package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.dao.jdbc.BandDao;
import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.service.impl.BandServiceImpl;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BandDaoSpringDataJpaTest {
    private final Logger logger = (Logger) LoggerFactory.getLogger(BandDaoSpringDataJpaTest.class);

    @Mock
    private BandDao mockedBandDao;

    @Mock
    private AlbumDao mockedAlbumDao;

    @InjectMocks
    private BandServiceImpl bandService;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown(){

    }
    
    @Test
    public void testSaveBandDto(){
        BandDto mockedBandDto = mock(BandDto.class);
        Band mockedBand = mock(Band.class);

        when(mockedBandDto.convertBandDtoToBand()).thenReturn(mockedBand);
        when(mockedBandDao.save(mockedBand)).thenReturn(mockedBand);
        when(mockedBand.convertBandToBandDto()).thenReturn(mockedBandDto);

        BandDto bandDto = bandService.save(mockedBandDto);

        verify(mockedBandDao, times(1)).save(mockedBand);
        verify(mockedBand, times(1)).convertBandToBandDto();
        verify(mockedBand, times(1)).convertBandToBandDto();
    }

    @Test
    public void testDeleteBandByName(){
        when(mockedBandDao.deleteByName(anyString())).thenReturn(false);
        boolean deleteResult = bandService.deleteByName(anyString());
        assertFalse(deleteResult);
        verify(mockedBandDao, times(1)).deleteByName(anyString());
    }

    @Test
    public void testGetBands(){
        List<Band> spyBandList = spy(ArrayList.class);

        Band mockedBand = mock(Band.class);

        spyBandList.add(mockedBand);
        spyBandList.add(mockedBand);
        spyBandList.add(mockedBand);

        BandDto mockedBandDto = mock(BandDto.class);

        when(mockedBand.convertBandToBandDto()).thenReturn(mockedBandDto);
        when(mockedBandDao.getBands()).thenReturn(spyBandList);

        List<Album> spyAlbumList = spy(ArrayList.class);
        List<BandDto> bandDtoList = bandService.getBands();

        assertEquals(3,bandDtoList.size());

        verify(mockedBandDao, times(1)).getBands();
        verify(mockedBand, times(3)).convertBandToBandDto();
        verify(mockedAlbumDao, times(1)).getAlbums();
    }
}
