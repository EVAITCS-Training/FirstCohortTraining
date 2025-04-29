package com.horrorcore.todoapp.repository;

import com.horrorcore.todoapp.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, String> {
    List<ToDo> findByTitleContainingIgnoreCase(String title);
}
