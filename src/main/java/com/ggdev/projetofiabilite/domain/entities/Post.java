package com.ggdev.projetofiabilite.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggdev.projetofiabilite.domain.services.UserService;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_posts")
public class Post implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @Column(name = "user_name")
    @Setter
    private String username;

    @Column(name = "post_description")
    @Setter
    private String message;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    public Post(Long id, User user, String message) {
        this.user = user;
        this.message = message;
        this.username = user.getName();
    }

    public Post(Long id, String message, Long userId) {
        UserService userService = new UserService();
        this.message = message;
        this.id = id;
        this.user = new User();
        this.username = user.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
