package com.ascendingdc.learnrestapi.dto;


import com.ascendingdc.learnrestapi.entity.Album;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AlbumDto {
    private Integer id;

    private String albumName;

    private String label;

    private String format;

    private String country;

    private LocalDate releaseDate;

    private List<GenreDto> genreDtoList = new ArrayList<>();

    public Album convertAlbumDtoToAlbum(){
        Album album = new Album();
        if(getId() != null)
            album.setId(getId());
        album.setAlbumName(getAlbumName());
        album.setLabel(getLabel());
        album.setFormat(getFormat());
        album.setCountry(getCountry());
        album.setReleaseDate(getReleaseDate());
        return album;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GenreDto> getGenreDtoList() {
        return genreDtoList;
    }

    public void setGenreDtoList(List<GenreDto> genreDtoList) {
        this.genreDtoList = genreDtoList;
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", label='" + label + '\'' +
                ", format='" + format + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate=" + releaseDate +
                ", genreDtoList=" + genreDtoList +
                '}';
    }
}
