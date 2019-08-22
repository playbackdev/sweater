package com.shvetsovg.sweater;

import com.shvetsovg.sweater.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin") //аннотация для установки пользователя, под которым мы хотим проводить тест (имитация авторизации и установки сессии)
@TestPropertySource("/application-test.properties") //какой файл использовать для тестирования приложения
@Sql(value = {"/users-list-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) //какие SQL запросы выполнить перед запуском теста
@Sql(value = {"/messages-list-after.sql", "/users-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) //и после запуска теста
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())//данная проверка пройдет успешно, если в текущем контексте установлена для пользователя веб-сессия
                .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("admin"));

    }

    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(4)); //карточек сообщений 4 штуки на странице
    }

    @Test
    public void filterMessageTest() throws Exception {
        this.mockMvc.perform(get("/main").param("filter", "my-tag"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(2))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=1]").exists())
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=3]").exists());
    }

    @Test
    public void addMessageToList() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/main")
                .file("file", "123".getBytes())
                .param("text","fifth")
                .param("tag", "new one")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]").exists())
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]/div/p[1]").string("fifth"))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]/div/i").string("tag: #new one"));
    }
}
