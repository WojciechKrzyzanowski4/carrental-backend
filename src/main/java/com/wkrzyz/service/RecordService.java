package com.wkrzyz.service;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords();

    void saveRecord(RecordDTO recordDTO);
    void saveRecordEntity(RecordEntity recordEntity);
}
