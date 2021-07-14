package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    private UserDao dao;

    public UsersController(UserDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "users", method = RequestMethod.GET)
    public List getUsers() {
        return dao.findAll();
    }

    @RequestMapping(path = "users/{id}", method = RequestMethod.GET)
    public String getUserNameById(@PathVariable long id) {
        return dao.getUsernameById(id);
    }
}
