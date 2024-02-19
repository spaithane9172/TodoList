package com.todo.list.TodoDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.Entities.MyTodo;
import com.todo.list.Repo.TodoRepository;

@Service
public class TodoDao {
    @Autowired
    TodoRepository todoRepo;

    public List<MyTodo> getAllTodo(){
       ArrayList<MyTodo> todoList=new ArrayList<>();
       todoRepo.findAll().forEach(todo->todoList.add(todo));
       return todoList;

    }
    @SuppressWarnings("null")
    public MyTodo getTodoById(Long id){
        return todoRepo.findById(id).get();
    }
    public boolean updateTodoItemStatus(Long id){
         MyTodo todo=getTodoById(id);
         todo.setStatus("Completed");
         return saveOrUpdateTodoItem(todo);
    }
    
    @SuppressWarnings("null")
    public boolean saveOrUpdateTodoItem(MyTodo todo){
        MyTodo todoUpdate=todoRepo.save(todo);
        if(getTodoById(todoUpdate.getId())!=null) return true;

        return false;

    }
    @SuppressWarnings({ "null", "unused" })
    public boolean deleteTodoItem(Long id){
        todoRepo.deleteById(id);
        if(todoRepo.findById(id).isEmpty()){ return true;}

        return false;
    }

}
