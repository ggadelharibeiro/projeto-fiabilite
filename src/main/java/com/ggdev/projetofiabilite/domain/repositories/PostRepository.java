package com.ggdev.projetofiabilite.domain.repositories;

import com.ggdev.projetofiabilite.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
