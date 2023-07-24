package com.ascendingdc.learnrestapi.controller;

import com.ascendingdc.learnrestapi.Controller.BandController;

import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.service.AlbumService;
import com.ascendingdc.learnrestapi.service.BandService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BandControllerTest {

    private final Logger logger = (Logger) LoggerFactory.getLogger(BandControllerTest.class);

    @Autowired
    private MockMvc mockedMvc;

    @MockBean
    private BandService mockedBandService;

    @MockBean
    private AlbumService mockedAlbumService;

    @InjectMocks
    private BandController bandController;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown(){

    }

    @Test
    public void testForAllBands() throws Exception{
        List<BandDto> bandDtoList = new ArrayList<>();

        BandDto bandDto = createBandDtoByName("testBand");
        int bandId = 100;
        bandDto.setId(bandId);
        bandDtoList.add(bandDto);

        when(mockedBandService.getBands()).thenReturn(bandDtoList);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(bandDtoList);

        String restUriForFindingAllBands = "/project2023/bands";
        mockedMvc.perform(get(restUriForFindingAllBands)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());

        verify(mockedBandService, times(1)).getBands();

    }

    private BandDto createBandDtoByName(String name) {
        BandDto bandDto = new BandDto();
        bandDto.setName(name);
        bandDto.setDescription(name = "description");
        return bandDto;
    }

    @Test
    public void testFindBandByBandId_happy_path() throws Exception{
        BandDto bandDto = createBandDtoByName("testBand");
        int bandId = 101;
        bandDto.setId(bandId);

        when(mockedBandService.getBandById(anyLong())).thenReturn(bandDto);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(bandDto);
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(bandDto);
        String restUriForGettingBandById = "/project2023/bands";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(restUriForGettingBandById, bandId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString);
        mockedMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());

        verify(mockedBandService, times(1)).getBands();
    }

    @Test
    public void testCreateBandId() throws Exception {
        String bandName = "test";
        BandDto inputBandDto = createBandDtoByName(bandName);
        BandDto savedBandDto = createBandDtoByName(bandName);
        int bandId = 100;
        savedBandDto.setId(bandId);

        String responseJsonString = JsonStringUtil.convertObjectToJsonString(inputBandDto);
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(savedBandDto);

        when(mockedBandService.save(inputBandDto)).thenReturn(savedBandDto);
        String restUriForCreatingBandDto = "/project2023/bands";

        mockedMvc.perform(post(restUriForCreatingBandDto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJsonString));

        verify(mockedBandService, times(1)).save(inputBandDto);
    }

    @Test
    public void testUpdateBandId() throws Exception {
        String bandName = "test";
        BandDto inputBandDto = createBandDtoByName(bandName);
        int bandId = 100;
        inputBandDto.setId(bandId);

        BandDto updatedBandDto = createBandDtoByName(bandName);
        updatedBandDto.setId(bandId);
        updatedBandDto.setDescription(updatedBandDto.getDescription() + "-updated");

        String responseJsonString = JsonStringUtil.convertObjectToJsonString(inputBandDto);
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(updatedBandDto);

        when(mockedBandService.update(inputBandDto)).thenReturn(updatedBandDto);
        String restUriForUpdatingBandDto = "/project2023/bands";

        mockedMvc.perform(put(restUriForUpdatingBandDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(responseJsonString))
                .andExpect(jsonPath("$.id", is(bandId)))
                .andExpect(jsonPath("$.name", comparesEqualTo(updatedBandDto.getName())))
                .andExpect(jsonPath("$.description", comparesEqualTo(updatedBandDto.getDescription())));

        verify(mockedBandService, times(1)).update(inputBandDto);
    }

    @Test
    public void testDeleteBandById_happy_path() throws Exception {
        when(mockedBandService.deleteById(anyLong())).thenReturn(true);

        Long bandId = 100L;

        String requestJsonString = JsonStringUtil.convertObjectToJsonString(bandId);
        String restUriForDeletingBandById = "/project2023/bands";

        mockedMvc.perform(delete(restUriForDeletingBandById, bandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isOk());


        verify(mockedBandService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteBandById_failed() throws Exception {
        when(mockedBandService.deleteById(anyLong())).thenReturn(false);

        Long bandId = 100L;

        String requestJsonString = JsonStringUtil.convertObjectToJsonString(bandId);
        String restUriForDeletingBandById = "/project2023/bands";

        mockedMvc.perform(delete(restUriForDeletingBandById, bandId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print())
                .andExpect(status().is4xxClientError());


        verify(mockedBandService, times(1)).deleteById(anyLong());
    }


}

