package com.joaocigana.mongoapi.instantiation;

import com.joaocigana.mongoapi.dto.AuthorDTO;
import com.joaocigana.mongoapi.dto.CommentDTO;
import com.joaocigana.mongoapi.entities.Post;
import com.joaocigana.mongoapi.entities.User;
import com.joaocigana.mongoapi.repositories.PostRepository;
import com.joaocigana.mongoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, LocalDateTime.of(2018, 2, 3, 15, 52), "Partiu Viagem!", "Indo para São Paulo amanhã, abraços!.", new AuthorDTO(maria));

        Post p2 = new Post(null, LocalDateTime.of(2020, 12, 25, 6, 12 ), "Bom Dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO ("Boa Viagem mano!", LocalDateTime.now(), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO ("Aproveite", LocalDateTime.now(), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO ("Tenha um ótimo dia!", LocalDateTime.now(), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1,c2));
        p2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);
    }
}
