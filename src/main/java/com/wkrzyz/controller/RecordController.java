package com.wkrzyz.controller;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import com.wkrzyz.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    private final RecordService recordService;
    @GetMapping
    public List<RecordDTO> getAllRecords(){
        System.out.println("all the records are being queried");
        return recordService.getAllRecords();
    }

}
