package com.wkrzyz.repository;

import com.wkrzyz.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEntityRepository extends JpaRepository<RecordEntity, Long> {
    // this is the place for the broken and lost and there is no code to be found here

}
