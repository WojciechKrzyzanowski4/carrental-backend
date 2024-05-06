package com.wkrzyz.service.impl;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import com.wkrzyz.mapper.RecordMapper;
import com.wkrzyz.repository.RecordEntityRepository;
import com.wkrzyz.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordEntityRepository recordEntityRepository;

    private final RecordMapper recordMapper;

    @Override
    public List<RecordDTO> getAllRecords(){
        return recordEntityRepository.findAll().stream()
                .map(recordMapper::fromRecordEntityToRecordDTO)
                .toList();
    }

    @Override
    public void saveRecord(RecordDTO recordDTO) {
        RecordEntity recordEntity = recordMapper.fromRecordDTOToRecordEntity(recordDTO);
        recordEntityRepository.save(recordEntity);
    }

    @Override
    public void saveRecordEntity(RecordEntity recordEntity) {
        recordEntityRepository.save(recordEntity);
    }
}
