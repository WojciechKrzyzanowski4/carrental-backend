package com.wkrzyz.service;

import com.wkrzyz.dto.ContactDTO;
import com.wkrzyz.dto.FeedbackDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.exception.NotFoundException;


public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void notifyUsersAboutDiscount(OfferEntity offerEntity);

    void sendFeedback(FeedbackDTO feedbackDTO) throws NotFoundException;

    void sendContact(ContactDTO contactDTO) throws NotFoundException;


}
