package main;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {

    @Autowired
    private ToDoRepository toDoListRepository;

    @RequestMapping("/")
    public String index(){
        return (new Date().toString());
    }
}
