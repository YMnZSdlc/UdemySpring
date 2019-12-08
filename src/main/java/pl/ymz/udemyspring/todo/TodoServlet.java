package pl.ymz.udemyspring.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private TodoRepository repository;

    TodoServlet(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        logger.info("Got request find all todos");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> toggleTodo(@PathVariable Integer id) {
        logger.warn("Got put request to toggle with id=" + id);
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        logger.warn("Method saveTodo: " + todo.toString());
        return ResponseEntity.ok(repository.save(todo));
    }
}
