package main.service;

import main.request.ToDoRequest;
import main.request.ToDoResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ServiceToDo {
    @NotNull
    List<ToDoResponse> findAll();

    @NotNull
    ToDoResponse findById(@NotNull Integer id);

    @NotNull
    ToDoResponse createToDo(@NotNull ToDoRequest request);

    @NotNull
    ToDoResponse update(@NotNull Integer id, @NotNull ToDoRequest request);

    void delete(@NotNull Integer id);
}
