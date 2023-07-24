package com.ascendingdc.learnrestapi.entity;


import com.ascendingdc.learnrestapi.dto.GenreDto;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createDate")
    private LocalDate createDate;

    public GenreDto convertGenreToGenreDto(){
        GenreDto genreDto = new GenreDto();
        genreDto.setId(getId());
        genreDto.setName(getName());
        genreDto.setDescription(getDescription());
        return genreDto;
    }

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Album> albums;
    public Set<Album> getAlbums(){
        if(albums == null)
            albums = new HashSet<Album>();
        return albums;
    }

    public void addAlbums(Album album){
        album.getGenres().add(this);
        this.getAlbums().add(album);
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

    @Override
    public String toString() {
        return "ProjectOne{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}