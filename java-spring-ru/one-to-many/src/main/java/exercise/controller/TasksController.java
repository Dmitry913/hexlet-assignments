package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDTO> index() {
        return taskRepository.findAll().stream().map(entity -> taskMapper.map(entity)).toList();
    }

    @GetMapping("/{id}")
    public TaskDTO show(@PathVariable Long id) {
        return taskMapper.map(taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO dto) {
        return taskMapper.map(taskRepository.save(taskMapper.map(dto)));
    }

    @PutMapping("/{id}")
    public TaskDTO update(@Valid @RequestBody TaskUpdateDTO dto, @PathVariable Long id) {
        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla"));
        taskMapper.update(dto, taskFromDB);
        if (dto.getAssigneeId() != null) {
            User newAssignee = userRepository.findById(dto.getAssigneeId()).orElseThrow(() -> new ResourceNotFoundException("bla"));
            taskFromDB.setAssignee(newAssignee);
        }
        taskRepository.save(taskFromDB);
        return taskMapper.map(taskFromDB);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    // END
}
