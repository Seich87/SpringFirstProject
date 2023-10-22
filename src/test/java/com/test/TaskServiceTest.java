package com.test;

import com.project.model.Status;
import com.project.model.Task;
import com.project.service.TaskService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskServiceTest {

    @Autowired
    public TaskService taskService;

    private static Long id;


    @Test
    @Order(1)
    public void whenTaskSaveThenCanGetTaskById() {
        Task task = new Task();
        task.setDescription("Погулять");
        task.setStatus(Status.IN_PROGRESS);
        taskService.saveTask(task);
        assertNotEquals(task.getId(), 0);
        id = task.getId();
        Task taskFromDB = taskService.getTaskById(task.getId());
        assertEquals(taskFromDB.getDescription(), "Погулять");
        assertEquals(taskFromDB.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    @Order(2)
    public void whenTaskSavedThenCanDeleteIt() {
        taskService.deleteTaskById(id);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.getTaskById(id));
        assertEquals(exception.getMessage(), "Task not found by id " + id);
    }
}
