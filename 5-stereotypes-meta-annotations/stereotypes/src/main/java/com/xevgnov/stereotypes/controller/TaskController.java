package com.xevgnov.stereotypes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xevgnov.stereotypes.domain.Task;
import com.xevgnov.stereotypes.service.TaskService;
import com.xevgnov.stereotypes.utility.TaskPrinter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/gui")
public class TaskController {
  private TaskService taskService;
  private TaskPrinter taskPrinter;

  public TaskController(TaskService taskService, TaskPrinter taskPrinter) {
    this.taskService = taskService;
    this.taskPrinter = taskPrinter;
  }

  @GetMapping
  public String show(Model model) {
    model.addAttribute("form",
        Task.builder().build());
    model.addAttribute("tasks",
        taskService.getAll());
    return "home";
  }

  @PostMapping("/add")
  public String add(@ModelAttribute("form") Task task, Model model) {
    Task addedTask = taskService.add(task);
    taskPrinter.printAsJson(addedTask);
    return "redirect:/gui";
  }

}
