package com.revature.todo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
@JacksonXmlRootElement
@Entity
public class Todo {

	@Id
	@GeneratedValue
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Unique Identifier for Todo")
	private long id;

	@Column(name = "title")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "The purpose of the Todo - what you need to do")
	private String title;

	@CreationTimestamp
	@Column(name = "created_on")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Automatically set at persistence")
	private LocalDate createdOn;

	@Column(name = "completed")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Whether the Todo has been completed")
	private boolean completed;
	
	@Column(name = "category")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Category of the todo")
	private String category;
	
	@Column(name = "user")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "User associated with the todo")
	private String user;
	
	@Column(name = "priority")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Whether the Todo is high priority")
	private boolean priority;
	
	@Column(name = "due_date")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Date the Todo must be completed")
	private LocalDate dueDate;

	public Todo() {}

	public Todo(TodoForm form) {
		this.id = form.getId();
		this.title = form.getTitle();
		//This line wasn't present in original implementation.  Oversight or confusion on my part?
		this.completed = form.isCompleted();
		this.category = form.getCategory();
		this.user = form.getUser();
		this.dueDate = form.getDueDate();
		this.priority = form.isPriority();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, createdOn, completed, category, user, priority, dueDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		//Since this is a Hibernate Entity, equality is determined by id.  (YMMV)
		return this.id == other.getId();
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Todo.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("title='" + title + "'")
						.add("createdOn=" + createdOn)
						.add("completed=" + completed)
						.add("category=" + category)
						.add("user=" + user)
						.add("priority=" + priority)
						.add("dueDate=" + dueDate)
						.toString();
	}
}
