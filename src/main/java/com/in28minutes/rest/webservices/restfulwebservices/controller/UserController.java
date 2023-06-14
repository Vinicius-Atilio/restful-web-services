package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.service.UserService;
import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;
    public UserController(UserService userDaoService) {
        this.service = userDaoService;
    }
    @GetMapping
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public User retrieveById(
            @PathVariable int id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);

        return user;
    }

    @PostMapping()
    public ResponseEntity<User> createUsers(
            @Valid
            @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable int id){
        service.delete(id);
    }


    /*
    @PutMapping("{id}")
    public User updateUser(
            @PathVariable int id,
            @RequestBody User user){
        return service.update(id, user);
    }

     */
}
