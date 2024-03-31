package com.wkrzyz.controller;

import com.wkrzyz.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class in only for basic implementation
 * @deprecated
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/send")
public class EmailController {


    private final EmailService emailService;

    /**
     * This works I fixed it, this shit hurt btw do not try to use yahoo for this, it made me heavily consider testing if I could fly
     * */
    @GetMapping
    public ResponseEntity<Void> sendEmails(){
        emailService.sendSimpleMessage("spider.wojciech@gmail.com", "Test email", "Hello, World!");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
