package com.in28minutes.rest.webservices.restfulwebservices.service;

import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    };

    public User findOne(int id) {
        Predicate<? super User> predicate = user ->
                user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void delete(int id) {
        Predicate<? super User> predicate = user ->
                user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User update(int id, User user){
        Predicate<? super User> predicate = use ->
                use.getId().equals(id);
        return null;
    }
}
