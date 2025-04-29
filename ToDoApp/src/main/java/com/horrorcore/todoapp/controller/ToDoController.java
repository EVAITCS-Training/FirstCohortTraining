package com.horrorcore.todoapp.controller;

import com.horrorcore.todoapp.entities.ToDo;
import com.horrorcore.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping(value = {"", "/"})
    public String getAllToDos(Model model) {
        List<ToDo> toDoList = toDoService.getAllToDos();
        // Add the list to the model if using a template engine
        model.addAttribute("toDoList", toDoList);
        return "index";
    }

    @GetMapping("/create")
    public String createToDo(Model model) {
        // Add an empty ToDo object to the model for the form
        model.addAttribute("toDo", new ToDo());
        return "create";
    }

    @PostMapping("/create")
    public String createToDo(ToDo toDo, Model model) {
        // Save the ToDo object
        toDoService.createToDo(toDo);
        // Redirect to the list of ToDos after creation
        return "redirect:/todos";
    }

    @GetMapping("/update/{id}")
    public String updateToDo(@PathVariable String id, Model model) {
        // Fetch the ToDo object by ID
        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            // Handle the case where the ToDo is not found
            model.addAttribute("error", "ToDo not found");
            return "error";
        }
        // Add the ToDo object to the model for the form
        model.addAttribute("toDo", toDo);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateToDo(@PathVariable String id, ToDo toDo, Model model) {
        // Update the ToDo object
        ToDo updatedToDo = toDoService.updateToDo(id, toDo);
        if (updatedToDo == null) {
            // Handle the case where the ToDo is not found
            model.addAttribute("error", "ToDo not found");
            return "error";
        }
        // Redirect to the list of ToDos after updating
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable String id) {
        // Delete the ToDo object
        toDoService.deleteToDo(id);
        // Redirect to the list of ToDos after deletion
        return "redirect:/todos";
    }
}
