package learn.patrimoineapi;

import learn.patrimoineapi.model.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PatrimoineTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create_patrimoine_ok() throws Exception {
        String json = "{\"possesseur\":\"John Doe\", \"derniereModification\":\"2023-09-23T10:15:30\"}";
        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    public void create_patrimoine_without_date_ok() throws Exception {
        String json = "{\"possesseur\":\"John Doe\"}";
        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    public void get_patrimoine_ok() throws Exception {
        String json = "{\"possesseur\":\"John Doe\", \"derniereModification\":\"2023-09-23T10:15:30\"}";

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void get_patrimoine_ko() throws Exception {
        mockMvc.perform(get("/patrimoines/10"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void create_patrimoine_with_current_date() throws Exception {
        String json = "{\"possesseur\":\"Jane Doe\"}";

        mockMvc.perform(put("/patrimoines/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void get_non_existent_patrimoine() throws Exception {
        mockMvc.perform(get("/patrimoines/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void update_existing_patrimoine() throws Exception {
        String initialJson = "{\"possesseur\":\"Alice\"}";
        mockMvc.perform(put("/patrimoines/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(initialJson))
                .andExpect(status().isOk());

        String updatedJson = "{\"possesseur\":\"Alice Updated\", \"derniereModification\":\"2023-09-23T10:15:30\"}";
        mockMvc.perform(put("/patrimoines/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(updatedJson));

        mockMvc.perform(get("/patrimoines/3"))
                .andExpect(status().isOk())
                .andExpect(content().json(updatedJson));
    }

    @Test
    public void create_patrimoine_invalid_json() throws Exception {
        String invalidJson = "{\"possesseur\":\"John Doe\", \"derniereModification\":\"invalid-date\"}";

        mockMvc.perform(put("/patrimoines/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}