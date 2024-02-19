package com.todo.list.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.todo.list.Entities.MyTodo;
import com.todo.list.TodoDao.TodoDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {
    @Autowired
    TodoDao dao;

    @GetMapping({ "/", "viewAllTodo" })
    public String viewAllTodoItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", dao.getAllTodo());
        model.addAttribute("message", message);
        return "viewTodoList";
    }

    @GetMapping("/updateTodoStatus/{id}")
    public String updateTodoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (dao.updateTodoItemStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Status Update Successfull");
            redirectAttributes.addFlashAttribute("msgType", "success");
            return "redirect:/viewAllTodo";
        }
        redirectAttributes.addFlashAttribute("message", "Status Update Faild");
        redirectAttributes.addFlashAttribute("msgType", "faild");

        return "redirect:/viewAllTodo";

    }

    @GetMapping("/addTodoItem")
    public String addTodoItem(Model model) {
        model.addAttribute("todo", new MyTodo());
        return "addTodoItem";
    }

    @PostMapping("/saveTodoItem")
    public String saveTodoItem(MyTodo myTodo, RedirectAttributes redirectAttributes) {
        if (myTodo.getTitle().length() != 0 && myTodo.getStatus().length() != 0 && myTodo.getDate() != null) {
            if (dao.saveOrUpdateTodoItem(myTodo)) {
                redirectAttributes.addFlashAttribute("message", "Item Added Successfully");
                redirectAttributes.addFlashAttribute("msgType", "success");

                return "redirect:/viewAllTodo";
            }
        }

        redirectAttributes.addFlashAttribute("message", "Item Not Added");
        redirectAttributes.addFlashAttribute("msgType", "faild");

        return "redirect:/addTodoItem";
    }

    @GetMapping("/editTodoItem/{id}")
    public String editTodoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", dao.getTodoById(id));
        return "editTodoItem";
    }

    @PostMapping("/saveEditTodoItem")
    public String saveEditTodoItem(MyTodo myTodo, RedirectAttributes redirectAttributes) {
        if (myTodo.getTitle().length() != 0 && myTodo.getStatus().length() != 0 && myTodo.getDate() != null) {
            if (dao.saveOrUpdateTodoItem(myTodo)) {
                redirectAttributes.addFlashAttribute("message", "Todo Item Updated Successfully");
                redirectAttributes.addFlashAttribute("msgType", "success");

                return "redirect:/viewAllTodo";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Update Todo Item Faild");
        redirectAttributes.addFlashAttribute("msgType", "faild");

        return "redirect:/editTodoItem/" + myTodo.getId();
    }

    @GetMapping("/deleteTodoItem/{id}")
    public String deleteTodoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (dao.deleteTodoItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Todo item deleted Successfully");
            redirectAttributes.addFlashAttribute("msgType", "success");

            return "redirect:/viewAllTodo";
        }
        redirectAttributes.addFlashAttribute("message", "Todo Item delete Faild");
        redirectAttributes.addFlashAttribute("msgType", "faild");

        return "redirect:/viewAllTodo";
    }

}
