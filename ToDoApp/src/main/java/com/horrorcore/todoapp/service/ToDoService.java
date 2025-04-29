package com.horrorcore.todoapp.service;

import com.horrorcore.todoapp.entities.ToDo;
import com.horrorcore.todoapp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public ToDo getToDoById(String id) {
        return toDoRepository.findById(id).orElse(null);
    }

    public ToDo updateToDo(String id, ToDo toDo) {
        if (toDoRepository.existsById(id)) {
            toDo.setId(UUID.fromString(id));
            return toDoRepository.save(toDo);
        }
        return null;
    }

    public void deleteToDo(String id) {
        toDoRepository.deleteById(id);
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public List<ToDo> getToDosByTitle(String title) {
        return toDoRepository.findByTitleContainingIgnoreCase(title);
    }
}
