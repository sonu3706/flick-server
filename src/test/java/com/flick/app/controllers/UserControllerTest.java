package com.flick.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flick.app.FlickServerApplication;
import com.flick.app.models.User;
import com.flick.app.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlickServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  private MockMvc mockMvc;
  @MockBean
  private UserService userService;
  @InjectMocks
  private UserController userController;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testLoginSuccess() throws Exception {
    Map<String, String> token = new HashMap<>();
    token.put("access_token", "123456");
    Mockito.when(userService.loginUser(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(token);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(getUserObject())))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
    Mockito.verify(userService, Mockito.times(1)).loginUser(Mockito.any(String.class), Mockito.any(String.class));
    Mockito.verifyNoMoreInteractions(userService);
  }

  @Test
  public void testRegisterSuccess() throws Exception {
    Map<String, Boolean> map = new HashMap<>();

    map.put("message", true);
    Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(map);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/register")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonToString(getUserObject())))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
    Mockito.verify(userService, Mockito.times(1)).registerUser(Mockito.any(User.class
    ));
    Mockito.verifyNoMoreInteractions(userService);
  }

  private User getUserObject() {
    User user = new User();
    user.setPassword("12345678");
    user.setUserEmail("chandan@test.com");
    user.setFirstName("Chandan");
    user.setLastName("mishra");
    return user;
  }

  // Parsing String format data into JSON format
  private static String jsonToString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
