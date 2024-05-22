package com.wkrzyz.repository;

import com.wkrzyz.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordEntityRepository extends JpaRepository<RecordEntity, Long> {

    List<RecordEntity> findAllByUser_id(Long id);

    List<RecordEntity> findAllByOffer_id(Long id);

}
