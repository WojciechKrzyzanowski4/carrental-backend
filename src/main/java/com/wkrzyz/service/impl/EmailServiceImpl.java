package com.wkrzyz.service.impl;

import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.service.EmailService;
import com.wkrzyz.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;


    @Value("${email.subject}")
    private String subject;

    private final String emailMatcher = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void notifyUsersAboutDiscount(OfferEntity offerEntity) {
        List<UserEntity> recepients = offerEntity.getLikedByUsers();
        StringBuilder text = new StringBuilder();
        CarEntity car = offerEntity.getCar();
        text.append("We are happy to inform, that the offer about")
            .append(car.getBrand())
            .append(" ")
            .append(car.getModel())
            .append(" ")
            .append(car.getYearOfManufacture())
            .append(" has been discounted");

        for(UserEntity user : recepients){
            if(user.getEmail().matches(emailMatcher)){
                // we found a user with a valid email,
                // this is important because of GitHub source
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject(subject);
                message.setText(text.toString());
                emailSender.send(message);
            }
        }
    }
}
