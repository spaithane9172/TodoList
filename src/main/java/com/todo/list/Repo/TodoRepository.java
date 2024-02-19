package com.todo.list.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.list.Entities.MyTodo;

@Repository
public interface TodoRepository extends JpaRepository<MyTodo,Long>{

    
}
