package com.wkrzyz.service;

import com.wkrzyz.entity.OfferEntity;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void notifyUsersAboutDiscount(OfferEntity offerEntity);


}
