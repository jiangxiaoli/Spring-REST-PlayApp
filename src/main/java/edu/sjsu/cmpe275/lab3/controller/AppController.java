package edu.sjsu.cmpe275.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {
    @RequestMapping(method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
    }
}