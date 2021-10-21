package com.ggdev.projetofiabilite.domain.services;

import com.ggdev.projetofiabilite.domain.entities.Comment;
import com.ggdev.projetofiabilite.domain.entities.User;
import com.ggdev.projetofiabilite.domain.repositories.CommentRepository;
import com.ggdev.projetofiabilite.domain.services.exceptions.DatabaseException;
import com.ggdev.projetofiabilite.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Comment insert(Comment obj){
        return repository.save(obj);
    }

    public Comment findById(Long id){
        Optional<Comment> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id){
       try {
           repository.deleteById(id);
       }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
       }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(id);
       }
    }


}
