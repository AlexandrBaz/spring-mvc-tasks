package main.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.request.ToDoRequest;
import main.request.ToDoResponse;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class ToDoService implements ServiceToDo {

    private final ToDoRepository toDoListRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ToDoResponse> findAll() {
        return toDoListRepository.findAll()
                .stream()
                .map(this::buildTodoResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    private ToDoResponse buildTodoResponse(@NotNull ToDo toDo){
        return new ToDoResponse()
                .setId(toDo.getId())
                .setTitle(toDo.getTitle())
                .setDescription(toDo.getDescription())
                .setCreationDate(toDo.getCreationDate())
                .setDone(toDo.getDone());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public ToDoResponse findById(@NotNull Integer id) {
        return toDoListRepository.findById(id)
                .map(this::buildTodoResponse)
                .orElseThrow(() -> new EntityNotFoundException("Task " + id + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public ToDoResponse createToDo(@NotNull ToDoRequest request) {
        ToDo toDo = buildToDoRequest(request);
        return buildTodoResponse(toDoListRepository.save(toDo));
    }

    @NotNull
    private ToDo buildToDoRequest(@NotNull ToDoRequest request) {
        return new ToDo()
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setCreationDate(LocalDateTime.now())
                .setDone(false);
    }

    @NotNull
    @Override
    @Transactional
    public ToDoResponse update(@NotNull Integer id, @NotNull ToDoRequest request) {
        ToDo toDo =  toDoListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task " + id + " is not found"));
        toDoUpdate(toDo, request);
        return buildTodoResponse(toDoListRepository.save(toDo));
    }

    private void toDoUpdate(@NotNull ToDo toDo, @NotNull ToDoRequest request) {
        ofNullable(request.getTitle()).map(toDo::setTitle);
        ofNullable(request.getDescription()).map(toDo::setDescription);
        ofNullable(request.getDone()).map(toDo::setDone);
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer id) {
        if (toDoListRepository.existsById(id)) {
            toDoListRepository.deleteById(id);
        } else {throw new EntityNotFoundException("Task " + id + " is not found");}

    }
}
