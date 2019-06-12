package com.github.dmn1k.jaxrsdemo;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TodoItems {
    
    @PersistenceContext(unitName = "todos")
    private EntityManager em;
    
    public Optional<TodoItem> find(long id){
        return Optional.ofNullable(em.find(TodoItem.class, id));
    }

    public void persist(TodoItem item){
        em.persist(item);
    }
}
