package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        Task task = createTaskInDB();
        MvcResult result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertThatJson(result.getResponse().getContentAsString()).isObject().containsEntry("id", task.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Task task = generateFakeTask();
        MvcResult result = mockMvc.perform(post("/tasks").content(om.writeValueAsString(task))
                        .header("Content-type", "application/json"))
                .andExpect(status().isCreated())
                .andReturn();
        Optional<Task> taskFromDB = taskRepository.findById(om.readValue(result.getResponse().getContentAsString(), Task.class).getId());
        assertTrue(taskFromDB.isPresent());
        assertEquals(task.getTitle(), taskFromDB.get().getTitle());
        assertEquals(task.getDescription(), taskFromDB.get().getDescription());
        assertThatJson(result.getResponse().getContentAsString()).isObject()
                .containsKey("id");
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = createTaskInDB();
        Map<String, String> updateField = Map.of("title", faker.lorem().paragraph());
        MvcResult result = mockMvc.perform(put("/tasks/" + task.getId()).header("Content-type", "application/json").content(om.writeValueAsString(updateField)))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(updateField.get("title"), taskRepository.findById(task.getId()).get().getTitle());
    }

    @Test
    public void testDelete() throws Exception {
        Task task = createTaskInDB();
        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());
        assertTrue(taskRepository.findById(task.getId()).isEmpty());
    }

    private Task createTaskInDB() {
        Task task = generateFakeTask();
        return taskRepository.save(task);
    }

    private Task generateFakeTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }
    // END
}
