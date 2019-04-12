package com.shvetsovg.sweater.controller;

import com.shvetsovg.sweater.domain.Message;
import com.shvetsovg.sweater.domain.User;
import com.shvetsovg.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> messages;

        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        }
        else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model){
        if(text != null && !text.isEmpty()) {
            //создаем сообщение
            Message message = new Message(text, tag, user);
            //сохраняем в базу
            messageRepo.save(message);
        }
        //берем из репозитория
        Iterable<Message> messages = messageRepo.findAll();
        //положили в модель
        model.put("messages", messages);
        model.put("filter", "");
        //выводим пользователю
        return "main";
    }
}
