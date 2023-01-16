package main.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "todolist")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "is_done")
    private Boolean done;
    private String title;
    private String description;

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", isDone=" + done +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDoList = (ToDo) o;
        return id == toDoList.id && creationDate.equals(toDoList.creationDate) && done.equals(toDoList.done) && title.equals(toDoList.title) && description.equals(toDoList.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, done, title, description);
    }
}
