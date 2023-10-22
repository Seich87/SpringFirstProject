package com.test;

import com.project.model.Status;
import com.project.model.Task;
import com.project.repository.TaskRepository;
import com.project.service.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTestWithoutSpring {

    @Mock
    public TaskRepository taskRepository;

    @Spy
    @InjectMocks
    public TaskService taskService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Погулять");
        task.setStatus(Status.IN_PROGRESS);

        Mockito.doAnswer(invocationOnMock -> Optional.of(task)).when(taskRepository).findById(anyLong());
    }

    @Test
    public void whenCallTaskByIdThenReturnCorrectTask() {
        Task task = taskService.getTaskById(1L);
        assertEquals(task.getId(), 1L);
        assertEquals(task.getDescription(), "Погулять");
        assertEquals(task.getStatus(), Status.IN_PROGRESS);
    }
}
