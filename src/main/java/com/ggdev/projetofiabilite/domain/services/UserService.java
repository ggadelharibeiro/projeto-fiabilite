package com.ggdev.projetofiabilite.domain.services;

import com.ggdev.projetofiabilite.domain.entities.Comment;
import com.ggdev.projetofiabilite.domain.entities.Post;
import com.ggdev.projetofiabilite.domain.entities.User;
import com.ggdev.projetofiabilite.domain.repositories.CommentRepository;
import com.ggdev.projetofiabilite.domain.repositories.PostRepository;
import com.ggdev.projetofiabilite.domain.repositories.UserRepository;
import com.ggdev.projetofiabilite.domain.services.exceptions.DatabaseException;
import com.ggdev.projetofiabilite.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
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

    public User update(Long id, User obj){
        try {
            User entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
    }


}
