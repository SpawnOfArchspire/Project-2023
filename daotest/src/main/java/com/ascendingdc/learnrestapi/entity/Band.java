package com.ascendingdc.learnrestapi.entity;
import com.ascendingdc.learnrestapi.dto.BandDto;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "band")

public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    public BandDto convertBandToBandDto(){
        BandDto bandDto = new BandDto();
        bandDto.setId(getId());
        bandDto.setName(getName());
        bandDto.setDescription(getDescription());
        return bandDto;
    }

    @OneToMany(mappedBy = "band", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Album> albums;

    public Set<Album> getAlbums(){
        if(albums == null)
            albums = new HashSet<Album>();
        return albums;
    }

    public void setAlbums(Set<Album> albums){
        this.albums = albums;
    }

    public void addAlbums(Album album){
        this.getAlbums().add(album);
        album.setBand(this);
    }

    public void removeAlbums(Album album){
        this.getAlbums().remove(album);
        album.setBand(null);
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

    @Override
    public String toString() {
        return "band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}