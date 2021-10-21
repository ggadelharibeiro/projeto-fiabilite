package com.ggdev.projetofiabilite.configurations;

import com.ggdev.projetofiabilite.domain.entities.Comment;
import com.ggdev.projetofiabilite.domain.entities.Post;
import com.ggdev.projetofiabilite.domain.entities.User;
import com.ggdev.projetofiabilite.domain.repositories.CommentRepository;
import com.ggdev.projetofiabilite.domain.repositories.PostRepository;
import com.ggdev.projetofiabilite.domain.repositories.UserRepository;
import com.ggdev.projetofiabilite.domain.services.PostService;
import com.ggdev.projetofiabilite.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration(value = "test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        try {
            User u1 = new User(null, "Mario");
            User u2 = new User(null, "Clelio");
            User u3 = new User(null, "Silvo");

            Post p1 = new Post(null, u1, "Ferias!");
            Post p2 = new Post(null, u1, "tedio... :/");
            Post p3 = new Post(null, u1, "o pai ta on!");

            userRepository.saveAll(Arrays.asList(u1, u2, u3));
            postRepository.saveAll(Arrays.asList(p1, p2, p3));

            u1.getPosts().add(p1);
            u1.getPosts().add(p2);
            u1.getPosts().add(p3);

            Comment c1 = new Comment(null, "assombracao", u3.getName(), u3, p1);
            Comment c2 = new Comment(null, "kkkkk bixin fein", u3.getName(), u3, p2);
            Comment c3 = new Comment(null, "newba", u3.getName(), u3, p1);

            u3.getComments().add(c1);
            u3.getComments().add(c2);
            u3.getComments().add(c3);

            commentRepository.saveAll(Arrays.asList(c1, c2, c3));
            postRepository.saveAll(Arrays.asList(p1, p2, p3));
            userRepository.saveAll(Arrays.asList(u3,u1));

            p1.getComments().add(c1);
            p1.getComments().add(c3);
            p2.getComments().add(c2);

            postRepository.saveAll(Arrays.asList(p1, p2));
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
