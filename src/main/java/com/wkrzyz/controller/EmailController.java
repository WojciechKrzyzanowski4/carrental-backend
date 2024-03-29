package com.wkrzyz.controller;

import com.wkrzyz.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/send")
public class EmailController {


    private final EmailService emailService;

    @GetMapping("/this")
    public ResponseEntity<Void> sendEmails(){
        emailService.sendSimpleMessage("spider.wojciech@gmail.com", "Test email", "Hello, World!");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
