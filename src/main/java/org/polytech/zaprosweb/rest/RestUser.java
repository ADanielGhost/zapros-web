package org.polytech.zaprosweb.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class RestUser {
    private List<User> users = new ArrayList<User>() {{
        add(new User(1L, "Danya1", "kek1@ltl"));
        add(new User(2L, "Danya2", "kek2@ltl"));
        add(new User(3L, "Danya3", "kek3@ltl"));
        add(new User(4L, "Danya4", "kek4@ltl"));
    }};

    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getOneUser(@PathVariable("userId") Long userId) {
        System.out.println("getOneUser: " + userId);
        return users.stream()
            .filter(x -> userId.equals(x.getId()))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return users;
    }

    @ResponseBody
    @RequestMapping(value = "/add/one", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOneUser(@RequestBody User user) {
        System.out.println("addOneUser: " + user);
        users.add(user);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addManyUsers(@RequestBody List<User> userList) {
        System.out.println("addManyUsers: ");
        userList.forEach(System.out::println);
        users.addAll(userList);
    }
}

class User {
    private long id;
    private String name;
    private String email;

    public User() {
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
