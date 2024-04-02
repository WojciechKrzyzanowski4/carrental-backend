package com.wkrzyz.service.impl;

import com.wkrzyz.service.OAuth2Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {


    @Override
    public String getEmailFromOAuth2Authentication() {
        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = securityContextHolder.getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            if ("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
                return oAuth2AuthenticationToken.getPrincipal().getAttributes().get("login").toString();
            }
        }
        return "";
    }
}
