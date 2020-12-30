
package com.davidsalter.todo.controller;

import com.davidsalter.todo.data.TodoDao;
import com.davidsalter.todo.domain.Todo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
/**
 *
 * @author david
 */
@Named
@RequestScoped
public class TodoController implements Serializable {

    @ManagedProperty("#{param.id}")
    private Long id;

    // Getters/setters ----------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    private Todo todo;

    @EJB
    private TodoDao dao;

    public List<Todo> getTodos() {
        return dao.findTodos();
    }

    public Todo getTodo() {
        if (todo == null)
            todo = new Todo();
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public String create() {
        todo = new Todo();
        return "create.jsf";
    }

    public String delete() {
        System.out.println(todo.getDescription());
        dao.delete(todo);
        return "todo.jsf";
    }
    
    public String edit() {
        todo = dao.findTodo(id);
        return "edit.jsf";
    }

    public String update() {
        System.out.println(todo.getDescription());
        dao.update(todo);
        return "todo.jsf";
    }

    public String persist() {
        dao.persist(todo);
        return "todo.jsf";
    }
}
