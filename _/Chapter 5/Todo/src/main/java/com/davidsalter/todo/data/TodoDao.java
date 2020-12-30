/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.davidsalter.todo.data;

import com.davidsalter.todo.domain.Todo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author david
 */
@Stateless
public class TodoDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(Todo todo) {
        em.persist(todo);
    }

    public void delete(Todo todo) {
        Todo t = em.merge(todo);
        em.remove(t);
    }

    public void update(Todo todo) {
        em.merge(todo);
    }

    public List<Todo> findTodos() {
        return (List<Todo>) em.createQuery("select t from Todo t")
                .getResultList();
    }

    public Todo findTodo(Long id) {
        return (Todo) em.find(Todo.class, id);
    }

}
