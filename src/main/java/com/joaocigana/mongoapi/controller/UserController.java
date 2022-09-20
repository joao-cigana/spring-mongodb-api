package com.joaocigana.mongoapi.controller;

import com.joaocigana.mongoapi.dto.UserDTO;
import com.joaocigana.mongoapi.entities.Post;
import com.joaocigana.mongoapi.entities.User;
import com.joaocigana.mongoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        List<User> list = userService.findAllUsers();
        List<UserDTO> dtoList = list.stream().map(user -> new UserDTO(user)).toList();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id){
        return ResponseEntity.ok().body(new UserDTO(userService.findUserById(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDTO dto){
        User user = userService.fromDTO(dto);
        user = userService.insertUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO dto, @PathVariable String id){
        User user = userService.fromDTO(dto);
        user.setId(id);
        user = userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable String id){
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user.getPosts());
    }



}
