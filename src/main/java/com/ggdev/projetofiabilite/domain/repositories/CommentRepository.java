package com.ggdev.projetofiabilite.domain.repositories;

import com.ggdev.projetofiabilite.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
