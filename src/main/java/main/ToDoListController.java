package main;

import lombok.RequiredArgsConstructor;
import main.request.ToDoRequest;
import main.request.ToDoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import main.service.ToDoService;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ToDoListController {

    @Autowired
    private final ToDoService toDoService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ToDoResponse> findAll() {
        return toDoService.findAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ToDoResponse findById(@PathVariable Integer id) {
        return toDoService.findById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoResponse  create(@RequestBody ToDoRequest request) {
        if(request.getTitle().isEmpty()){
            throw new RuntimeException("Title isEmpty");
        } else if (request.getDescription().isEmpty()){
            throw new RuntimeException("Description isEmpty");
        }
        return toDoService.createToDo(request);
    }

    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ToDoResponse update(@PathVariable Integer id, @RequestBody ToDoRequest request) {
        return toDoService.update(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id) {
        toDoService.delete(id);
    }

}
