package com.ascendingdc.learnrestapi.dto;

import com.ascendingdc.learnrestapi.entity.Band;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class BandDto {
    private Integer id;

    private String name;

    private String description;

    private List<AlbumDto> albumList = new ArrayList<>();

    public Band convertBandDtoToBand(){
        Band band = new Band();
        if(getId() != null)
            band.setId(getId());
        band.setName(getName());
        band.setDescription(getDescription());
        return band;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public List<AlbumDto> getAlbumList () {
        return albumList;
    }

    public void setAlbumList (List < AlbumDto > albumList) {
        this.albumList = albumList;
    }
    @Override
    public String toString () {
        return "BandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", albumList=" + albumList +
                '}';
        }
    }
