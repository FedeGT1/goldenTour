package com.goldentour.jee.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accomodation")
public class Accomodation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telephone")
    private int telephone;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accomodation_type")
    private AccomodationType accomodationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination")
    private Destination destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AccomodationType getAccomodationType() {
        return accomodationType;
    }

    public void setAccomodationType(AccomodationType accomodationType) {
        this.accomodationType = accomodationType;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Accomodation tmp = (Accomodation) obj;
        return id.equals(tmp.getId());
    }

}