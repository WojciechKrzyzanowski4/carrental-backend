package com.wkrzyz.service;

import com.wkrzyz.dto.ContactDTO;
import com.wkrzyz.dto.FeedbackDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.exception.NotFoundException;
import jakarta.mail.MessagingException;
import org.springframework.web.client.HttpClientErrorException;


public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void notifyUsersAboutDiscount(OfferEntity offerEntity) throws NotFoundException, MessagingException;

    void sendFeedback(FeedbackDTO feedbackDTO) throws NotFoundException, MessagingException;

    void sendContact(ContactDTO contactDTO) throws NotFoundException, MessagingException;

    void sendConfirmation(String email) throws NotFoundException, MessagingException;
}
