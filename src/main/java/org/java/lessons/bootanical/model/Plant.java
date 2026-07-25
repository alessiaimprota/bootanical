package org.java.lessons.bootanical.model;

import java.security.Identity;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import java.math.BigDecimal;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della pianta non può essere vuoto")
    private String name;
    @Lob
    @NotBlank(message = "La descrizione non può essere vuota")
    private String description;

    @NotNull(message = "Il prezzo è obbligatorio")
    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private BigDecimal price;

    private String imgUrl;

    @OneToMany(mappedBy = "plant")
    @JsonManagedReference
    private List<Care> cares;

    public List<Care> getCares() {
        return this.cares;
    }

    public void setCares(List<Care> cares) {
        this.cares = cares;
    }

    public Plant() {
    }

    /* Costruttore */
    public Plant(Integer id, String name, String description, BigDecimal price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    /* getter e setter */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.name, this.description, this.price, this.imgUrl);
    }
}
