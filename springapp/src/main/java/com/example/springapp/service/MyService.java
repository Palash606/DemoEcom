package com.example.springapp.service;

import com.example.springapp.exception.InvalidCredentialsException;
import com.example.springapp.model.User;
import com.example.springapp.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {
    List<User> list;

    {
        list = new ArrayList<>();
        User u1 = new User();
        u1.setUsername("harry");
        u1.setPassword("potter");
        list.add(u1);

        User u2 = new User();
        u2.setUsername("ronald");
        u2.setPassword("weasley");
        list.add(u2);

        User u3 = new User();
        u3.setUsername("hermione");
        u3.setPassword("granger");
        list.add(u3);
    }

    @Autowired
    private MyRepository myRepository;

    public User isValid(String username, String password) throws InvalidCredentialsException {
        List<User> userList = list.stream().filter(e -> e.getUsername().equals(username) &&
                e.getPassword().equals(password)).toList();
        if (userList.isEmpty())
            throw new InvalidCredentialsException("Invalid Credentials, Login Failed!!!");

        return userList.get(0);
    }
}