package com.ggdev.projetofiabilite.domain.controllers;

import com.ggdev.projetofiabilite.domain.entities.Comment;
import com.ggdev.projetofiabilite.domain.entities.Post;
import com.ggdev.projetofiabilite.domain.entities.User;
import com.ggdev.projetofiabilite.domain.services.CommentService;
import com.ggdev.projetofiabilite.domain.services.PostService;
import com.ggdev.projetofiabilite.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class ApplicationController {

    //SERVICES
    @Autowired
    private UserService service;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //GET METHODS
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<User> findById(@PathVariable Long id){

        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{id}/posts/all")
    public ResponseEntity<List<Post>> findAllPosts(){
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId){
        Post obj = postService.findById(postId);
        obj.setUser(service.findById(postId));
        return ResponseEntity.ok().body(obj);
    }


    //DELETE METHODS
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        Post post = postService.findById(postId);
        User user = post.getUser();
        List<Comment> list = post.getComments();

        //Remove o user vinculado ao post
        user.getPosts().remove(post);
        //Remove os comentarios vinculados ao post
        for(Comment c : list){
            c.setUser(null);
            c.setPost(null);
            commentService.delete(c.getId());
        }
        post.getComments().removeAll(post.getComments());
        post.setUser(null);

        service.update(user.getId(), user);
        postService.update(postId, post);
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        Comment comment = commentService.findById(commentId);
        User user = comment.getUser();
        Post post = comment.getPost();

        post.getComments().remove(commentId);
        user.getComments().remove(commentId);

        service.update(user.getId(), user);
        postService.update(post.getId(), post);
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }


    //PUT METHOD (SO O USUARIO PODE ATUALIZAR, POSTS E COMENTARIOS TEM QUE SER APAGADOS E REESCRITOS)
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


    //POST METHODS
    @PostMapping(value = "/{id}/posts")
    public ResponseEntity<Post> post(@RequestBody Post obj, @PathVariable Long id){
        User user = service.findById(id);
        obj.setUser(user);
        obj.setUsername(user.getName());
        user.getPosts().add(obj);
        service.update(user.getId(), user);
        postService.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{id}/posts/{postId}/comments")
    public ResponseEntity<Comment> comment(@RequestBody Comment obj, @PathVariable Long id, @PathVariable Long postId){
        User user = service.findById(id);
        Post post = postService.findById(postId);

        obj.setPost(post);
        obj.setUser(user);
        obj.setUsername(user.getName());

        user.getComments().add(obj);
        post.getComments().add(obj);

        service.update(id, user);
        postService.update(postId, post);
        commentService.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
