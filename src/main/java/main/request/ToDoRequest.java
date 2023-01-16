package main.request;

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ToDoRequest {
        private int id;
        private LocalDateTime creationDate;
        private Boolean done;
        private String title;
        private String description;
}
