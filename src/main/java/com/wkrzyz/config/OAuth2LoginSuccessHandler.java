package com.wkrzyz.config;

import com.wkrzyz.entity.RegistrationSource;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.entity.UserRole;
import com.wkrzyz.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    @Value("${frontend.url}")
    private String frontendURL;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        // using the name of the provider we will
        // know what sort of data we have to access
        // in the authentication object we got from the provider
        if("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())){

            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();

            Object nameObj = attributes.getOrDefault("login", "");
            Object emailObj = attributes.getOrDefault("email", "");
            String nameV ="";
            String emailV="";
            if(nameObj!=null){
                nameV=nameObj.toString();
            }else{
                throw new RuntimeException("Name was not set in git hub, this is impossible but you managed it");
            }
            if(emailObj!=null){
                emailV=emailObj.toString();
            }else{
                emailV = nameV;
            }
            final String email = emailV;
            final String name = nameV;

            userService.findUserByEmail(email)
                    .ifPresentOrElse(user -> {
                        //user is present in the database
                        DefaultOAuth2User userNew = new DefaultOAuth2User(
                                List.of(new SimpleGrantedAuthority(user.getRole().name())),
                                attributes, "id");
                        Authentication securityAuth = new OAuth2AuthenticationToken(userNew, List.of(new SimpleGrantedAuthority(user.getRole().name())),
                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    }, () -> {
                        //user is not present in the database
                        UserEntity userEntity = new UserEntity();
                        userEntity.setRole(UserRole.ROLE_USER);
                        userEntity.setEmail(email);
                        userEntity.setName(name);
                        userEntity.setSource(RegistrationSource.GITHUB);
                        userService.saveUser(userEntity);
                        DefaultOAuth2User userNew = new DefaultOAuth2User(
                                List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                                attributes, "id");
                        Authentication securityAuth = new OAuth2AuthenticationToken(userNew, List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    });
        }

        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontendURL);
        super.onAuthenticationSuccess(request, response, authentication);
    }





}
