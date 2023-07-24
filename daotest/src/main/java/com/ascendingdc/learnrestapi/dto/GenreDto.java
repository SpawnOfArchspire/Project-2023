package com.ascendingdc.learnrestapi.dto;


import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.entity.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GenreDto {
    private Integer id;


    private String name;


    private String description;


    private LocalDate createDate;

    private List<AlbumDto> albumDtoList = new ArrayList<>();

    public Genre convertGenreDtoToGenre(){
        Genre genre = new Genre();
        if(getId() != null)
            genre.setId(getId());
        genre.setName(getName());
        genre.setDescription(getDescription());
        return genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<AlbumDto> getAlbumDtoList() {
        return albumDtoList;
    }

    public void setAlbumDtoList(List<AlbumDto> albumDtoList) {
        this.albumDtoList = albumDtoList;
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", albumDtoList=" + albumDtoList +
                '}';
    }
}
