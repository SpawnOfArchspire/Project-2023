package com.ascendingdc.learnrestapi.Controller;

import com.ascendingdc.learnrestapi.dto.BandDto;
import com.ascendingdc.learnrestapi.exception.BandNotFoundException;
import com.ascendingdc.learnrestapi.exception.ExceptionResponse;
import com.ascendingdc.learnrestapi.service.AlbumService;
import com.ascendingdc.learnrestapi.service.BandService;
import com.ascendingdc.learnrestapi.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/project2023")
public class BandController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BandService bandService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private GenreService genreService;

    @GetMapping(value = "/bands", produces = "application/json")
    public ResponseEntity<List<BandDto>> findAllBands(){
        List<BandDto> bandDtoList = bandService.getBands();
        return new ResponseEntity<>(bandDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/bands/param_practice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findBandDtoByRequestParamBandId(@RequestParam("id") Long bandId){
        BandDto bandDto = null;
        ResponseEntity<Object> responseEntity = null;
        try{
            bandDto = bandService.getBandById(bandId);
            responseEntity = new ResponseEntity<>(bandDto, HttpStatus.OK);
        } catch (BandNotFoundException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse("BandNotFoundException", e.getMessage());
            responseEntity = new ResponseEntity<>(bandDto, HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }

    @GetMapping(value = "/bands/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findBandDtoByBandName(@PathVariable("name") String bandName){
        BandDto bandDto = null;
        ResponseEntity<Object> responseEntity = null;
        try{
            bandDto = bandService.getBandByName(bandName);
            responseEntity = new ResponseEntity<>(bandDto, HttpStatus.OK);
        } catch (BandNotFoundException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse("BandNotFoundException", e.getMessage());
            responseEntity = new ResponseEntity<>(bandDto, HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }

    @PostMapping(value = "/bands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDto> saveBandDto(@RequestBody BandDto bandDto){
        BandDto savedBandDto = bandService.save(bandDto);
        return new ResponseEntity<>(savedBandDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/bands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BandDto updateBandDto(@RequestBody BandDto bandDto){
        BandDto updatedBandDto = bandService.update(bandDto);
        return updatedBandDto;
    }

    @DeleteMapping(value = "/bands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBandDto(@RequestBody BandDto bandDto){
        boolean deleteResult = bandService.delete(bandDto);
        if(deleteResult){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
