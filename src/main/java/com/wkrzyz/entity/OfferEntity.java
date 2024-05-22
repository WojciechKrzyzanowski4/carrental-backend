package com.wkrzyz.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "offer_id")
    private List<ReservationEntity> reservations;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offer_id")
    private List<RecordEntity> records;

}
