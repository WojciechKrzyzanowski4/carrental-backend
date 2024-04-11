package com.wkrzyz.controller;
import com.wkrzyz.entity.RegistrationSource;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.entity.UserRole;
import com.wkrzyz.service.OAuth2Service;
import com.wkrzyz.service.OfferService;
import com.wkrzyz.service.ReservationService;
import com.wkrzyz.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OAuth2Service oAuth2Service;


    private final static UserEntity mockUser = new UserEntity();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mockUser.setName("WojciechKrzyzanowski4");
        mockUser.setEmail("WojciechKrzyzanowski4");
        mockUser.setRole(UserRole.ROLE_ADMIN);
        mockUser.setSource(RegistrationSource.GITHUB);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void restControllerTestForCurrentUser() throws Exception {
        //more tests to do
        userService.saveUser(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

}