package com.revature.todo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTests {
	
	private static final int TEST_TODO_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TodoService todoService;
	
	@BeforeEach
	public void setUp() {
		List<Todo> todos = Arrays.asList(
				new Todo(),
				new Todo(),
				new Todo()
				);
		todos.get(0).setId(1);
		todos.get(0).setTitle("First Todo");
		todos.get(0).setCompleted(false);
		todos.get(1).setId(2);
		todos.get(1).setTitle("Second Todo");
		todos.get(1).setCompleted(false);
		todos.get(2).setId(3);
		todos.get(2).setTitle("Third Todo");
		todos.get(2).setCompleted(false);
		when(todoService.findAllTodos()).thenReturn(todos);
		when(todoService.findById(longThat(id -> id == TEST_TODO_ID))).thenReturn(todos.get(0));
		when(todoService.findById(longThat(id -> id != TEST_TODO_ID))).thenThrow(new TodoNotFoundException(0L));
	}
	
	@Test
	void getAllTodos() throws Exception {
		mockMvc.perform(get("/todos")).andExpect(status().isOk());
	}
	
	@Test
	void findTestTodo() throws Exception {
		mockMvc.perform(get("/todos/{todoId}", TEST_TODO_ID)).andExpect(status().isOk());
	}
	
	@Test
	void failToFindTodo() throws Exception {
		mockMvc.perform(get("/todos/{todoId}", TEST_TODO_ID + 1)).andExpect(status().is(404));
	}
	
	@Test
	void sendClientErrorWhenBadPathVariable() throws Exception {
		mockMvc.perform(patch("/todos/hello")).andExpect(status().is(400));
	}

}
