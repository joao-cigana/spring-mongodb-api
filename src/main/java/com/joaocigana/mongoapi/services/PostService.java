package com.joaocigana.mongoapi.services;

import com.joaocigana.mongoapi.entities.Post;
import com.joaocigana.mongoapi.exceptions.ObjectNotFoundException;
import com.joaocigana.mongoapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post findPostById(String id){
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

    }

    public List<Post> findPostByTitle(String text){
        return postRepository.searchTitle(text);
    }

}
