package com.joaocigana.mongoapi.controller;

import com.joaocigana.mongoapi.controller.util.URL;
import com.joaocigana.mongoapi.dto.UserDTO;
import com.joaocigana.mongoapi.entities.Post;
import com.joaocigana.mongoapi.entities.User;
import com.joaocigana.mongoapi.services.PostService;
import com.joaocigana.mongoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findUserById(@PathVariable String id){
        return ResponseEntity.ok().body(postService.findPostById(id));
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findUserByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        return ResponseEntity.ok().body(postService.findPostByTitle(text));
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> searchByTextInDateRange(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String min,
            @RequestParam(value = "maxDate", defaultValue = "") String max){
        text = URL.decodeParam(text);
        LocalDateTime minDate = URL.convertDate(min, LocalDateTime.of(1970, 1, 1, 00, 00));
        LocalDateTime maxDate = URL.convertDate(max, LocalDateTime.now());
        return ResponseEntity.ok().body(postService.searchByTextInDateRange(text, minDate, maxDate));
    }

}
