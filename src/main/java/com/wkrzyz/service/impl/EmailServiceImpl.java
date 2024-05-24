package com.wkrzyz.service.impl;

import com.wkrzyz.dto.ContactDTO;
import com.wkrzyz.dto.FeedbackDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.EmailService;
import com.wkrzyz.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
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

                //change simple mail message
                //https://stackoverflow.com/questions/5068827/how-do-i-send-an-html-email
                //https://mailtrap.io/blog/java-send-html-email/
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject(subject);
                message.setText(text.toString());
                emailSender.send(message);
            }
        }
    }

    @Override
    public void sendFeedback(FeedbackDTO feedbackDTO) throws NotFoundException {
        if(feedbackDTO.getEmail().matches(emailMatcher)){
            //assemble text and use html template

            //change simple mail message
            //https://stackoverflow.com/questions/5068827/how-do-i-send-an-html-email
            //https://mailtrap.io/blog/java-send-html-email/
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(subject);
            message.setSubject(feedbackDTO.getEmail());
            message.setText("text.toString()");
            emailSender.send(message);

        }else{
            throw new NotFoundException("email was not valid");
        }
    }

    @Override
    public void sendContact(ContactDTO contactDTO) throws NotFoundException {
        if(contactDTO.getEmail().matches(emailMatcher)){
            //assemble text and use html template

            //change simple mail message
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(subject);
            message.setSubject(contactDTO.getEmail());
            message.setText("text.toString()");
            emailSender.send(message);
        }else{
            throw new NotFoundException("email was not valid");
        }
    }
}
