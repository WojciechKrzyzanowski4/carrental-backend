package com.wkrzyz.repository;

import com.wkrzyz.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
@Repository
public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity findByReservationDate(Date date);


}
