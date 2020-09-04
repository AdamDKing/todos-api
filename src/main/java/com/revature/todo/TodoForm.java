package com.revature.todo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class TodoForm {

	@ApiModelProperty(notes = "(Optional) The ID of the Todo to manipulate")
	private long id;

	/**
	 *  What the actual task you need to do is
	 */
	@ApiModelProperty(notes = "Desired `title` of the Todo")
	@NotBlank(message = "Title must not be blank")
	private String title;

	@ApiModelProperty(notes = "Desired `completed` of the Todo")
	private boolean completed;
	
	@ApiModelProperty(notes = "(Optional) Desired category of the todo")
	private String category;
	
	@ApiModelProperty(notes = "(Optional) Desired user associated with the todo")
	private String user;
	
	@ApiModelProperty(notes = "(Optional) Whether the Todo is high priority")
	private boolean priority;
	
	@ApiModelProperty(notes = "(Optional) Date the Todo must be completed. YYYY-MM-DD")
	private LocalDate dueDate;

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
		return Objects.hash(id, title, completed, category, priority, dueDate, user);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoForm other = (TodoForm) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (completed != other.completed)
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id != other.id)
			return false;
		if (priority != other.priority)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", TodoForm.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("title='" + title + "'")
						.add("complete=" + completed)
						.add("category=" + category)
						.add("user=" + user)
						.add("priority=" + priority)
						.add("dueDate=" + dueDate)
						.toString();
	}
}
