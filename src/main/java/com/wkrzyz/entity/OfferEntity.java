package com.wkrzyz.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.util.List;

@Entity
@Table(name="offer_table")
@Data
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private String description;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CarEntity car;

    @ManyToMany(mappedBy = "likedOffers")
    private List<UserEntity> likedByUsers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ReservationEntity> reservations;

}
