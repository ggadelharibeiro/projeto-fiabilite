package com.ggdev.projetofiabilite.domain.services;

import com.ggdev.projetofiabilite.domain.entities.Post;
import com.ggdev.projetofiabilite.domain.repositories.PostRepository;
import com.ggdev.projetofiabilite.domain.services.exceptions.DatabaseException;
import com.ggdev.projetofiabilite.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(Long id){
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(id);
        }
    }

    public Post insert(Post obj){
        return repository.save(obj);
    }

    public Post update(Long id, Post obj){
        try {
            Post entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Post entity, Post obj){
        entity.setMessage(obj.getMessage());
    }

}
