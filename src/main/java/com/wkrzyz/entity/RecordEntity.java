package com.wkrzyz.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "record_table")
@Data
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name="record_date")
    private Date recordDate;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
