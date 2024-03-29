package com.wkrzyz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    //here we need to implement all the relations in the table
    //for today i just want to make a component that will allow me to select a date
    //and pass it into sql



}
