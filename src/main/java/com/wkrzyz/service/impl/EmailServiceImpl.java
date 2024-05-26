package com.wkrzyz.service.impl;

import com.wkrzyz.dto.ContactDTO;
import com.wkrzyz.dto.FeedbackDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.EmailService;
import com.wkrzyz.service.OfferService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    //this is the company email!!!
    @Value("${email.subject}")
    private String subject;

    private final String emailMatcher = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void notifyUsersAboutDiscount(OfferEntity offerEntity) throws NotFoundException, MessagingException{
        List<UserEntity> recipients = offerEntity.getLikedByUsers();
        StringBuilder carName = new StringBuilder();
        CarEntity car = offerEntity.getCar();
        carName.append(car.getBrand())
            .append(" ")
            .append(car.getModel())
            .append(" ")
            .append(car.getYearOfManufacture());

        for(UserEntity user : recipients){
            if(user.getEmail().matches(emailMatcher)){
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(subject);
                helper.setSubject("Your Feedback");
                Context context = new Context();
                context.setVariable("offer", offerEntity.getName());
                context.setVariable("car", carName.toString());
                String htmlContent = templateEngine.process("discount", context);
                helper.setText(htmlContent, true);
                System.out.println("sending message");
                emailSender.send(message);

            }
        }
    }

    @Override
    public void sendFeedback(FeedbackDTO feedbackDTO) throws NotFoundException, MessagingException {
        if(feedbackDTO.getEmail().matches(emailMatcher)){

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(subject);
            helper.setSubject("Your Feedback");

            Context context = new Context();

            context.setVariable("email", feedbackDTO.getEmail());
            context.setVariable("overview", feedbackDTO.getOverview());
            context.setVariable("description", feedbackDTO.getDescription());
            context.setVariable("category", feedbackDTO.getType());

            String htmlContent = templateEngine.process("feedback", context);

            helper.setText(htmlContent, true);
            System.out.println("sending message");
            emailSender.send(message);
            sendConfirmation(feedbackDTO.getEmail());
        }else{
            throw new NotFoundException("email was not valid");
        }
    }

    @Override
    public void sendContact(ContactDTO contactDTO) throws NotFoundException, MessagingException {
        if(contactDTO.getEmail().matches(emailMatcher)){

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(subject);
            helper.setSubject("Your message");

            Context context = new Context();

            context.setVariable("email", contactDTO.getEmail());
            context.setVariable("message", contactDTO.getMessage());

            String htmlContent = templateEngine.process("contact", context);

            helper.setText(htmlContent, true);
            System.out.println("sending message");
            emailSender.send(message);
            sendConfirmation(contactDTO.getEmail());

        }else{
            throw new NotFoundException("email was not valid");
        }
    }

    @Override
    public void sendConfirmation(String email) throws NotFoundException, MessagingException {
        if(email.matches(emailMatcher)){
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Your message");
            Context context = new Context();
            String htmlContent = templateEngine.process("confirmation", context);
            helper.setText(htmlContent, true);
            System.out.println("sending message");
            emailSender.send(message);
        }else{
            throw new NotFoundException("email was not valid");
        }
    }
}
