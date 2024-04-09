package com.wkrzyz.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
@Table(name="reservation_table")
@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="reservation_date")
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
