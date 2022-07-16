package com.websocket.controllers;

import com.websocket.models.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping(value="/users")
    public UserModel getUser(){
        UserModel user = new UserModel();
        user.setFirstName("TestFirst");
        user.setLastName("TestLast");

        // Try connect websocket and send received message to client



        return user;
    }

}
