package com.ascendingdc.learnrestapi.entity;
import com.ascendingdc.learnrestapi.dto.AlbumDto;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table

public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "label")
    private String label;

    @Column(name = "format")
    private String format;

    @Column(name = "country")
    private String country;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public AlbumDto convertAlbumToAlbumDto(){
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(getId());
        albumDto.setAlbumName(getAlbumName());
        albumDto.setLabel(getLabel());
        albumDto.setFormat(getFormat());
        albumDto.setCountry(getCountry());
        albumDto.setReleaseDate(getReleaseDate());
        return albumDto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id")
    private Band band;



    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "album_genre",
            joinColumns = {@JoinColumn(name = "album_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private Set<Genre> genres;

    public void setBand(Band band){
        this.band = band;
    }

    public Set<Genre> getGenres(){
        if(genres == null)
            genres = new HashSet<Genre>();
        return genres;
    }

    public void addGenres(Genre genre){
        this.getGenres().add(genre);
        genre.getAlbums().add(this);
    }

    public void removeGenres(Genre genre){
        this.getGenres().remove(genre);
        genre.getAlbums().remove(this);
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

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", label='" + label + '\'' +
                ", format='" + format + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
