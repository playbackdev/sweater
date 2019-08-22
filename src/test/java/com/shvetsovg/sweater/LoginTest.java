package com.shvetsovg.sweater;

import com.shvetsovg.sweater.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc//для автоматической подмены структуры классов слоя MVC без использования RestTemplate
@TestPropertySource("/application-test.properties") //какой файл использовать для тестирования приложения
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Guest!")));
    }

    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection()) /*проверка что система вернет статус 300 - редирект на страницу логина*/
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @Sql(value = {"/users-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) //какие SQL запросы выполнить перед запуском теста
    @Sql(value = {"/users-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) //и после запуска теста
    public void correctLoginText() throws Exception {
        this.mockMvc.perform(formLogin().user("admin").password("123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(post("login").param("user", "Alfred"))
                .andDo(print())
                .andExpect(status().isForbidden()); //403 status
    }
}
