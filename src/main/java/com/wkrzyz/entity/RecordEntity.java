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
    private Integer status;

//    @Column(name="payment_date")
//    private Date paymentDate;

    @Column(name="record_date")
    private Date recordDate;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
