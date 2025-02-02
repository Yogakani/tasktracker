package com.shell.tasktracker.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shell.tasktracker.response.Tasks;
import com.shell.tasktracker.util.FileUtil;
import com.shell.tasktracker.util.JsonUtil;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ShellComponent
public class TaskManager {

    @ShellMethod(key = "add", value = "Add new Task")
    public String addTask(String task) throws IOException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);

        if (CollectionUtils.isEmpty(tasksList)) {
            tasksList = new ArrayList<>();
        }

        Tasks tasks = new Tasks();
        int taskId = FileUtil.getMaxId(tasksList) + 1;
        tasks.setId(taskId);
        tasks.setDescription(task);
        tasks.setStatus("todo");
        tasks.setCreatedAt(LocalDateTime.now());
        tasks.setUpdatedAt(LocalDateTime.now());

        tasksList.add(tasks);

        JsonUtil.writeJson(tasksList, taskFile);

        return "Task added successfully (ID: " + taskId + ")";
    }

    @ShellMethod(key = "update", value = "Update existing Task")
    public String updateTask(int taskId, String updatedTask) throws IOException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);

        if (!CollectionUtils.isEmpty(tasksList)) {
            Tasks tasks = FileUtil.getTasksById(taskId, tasksList);
            if (Objects.nonNull(tasks)) {
                tasks.setDescription(updatedTask);
                tasks.setUpdatedAt(LocalDateTime.now());
                JsonUtil.writeJson(tasksList, taskFile);
                return "Task updated successfully (ID: " + taskId + ")";
            } else {
                return "No task found with ID : " + taskId;
            }
        } else {
            return "No task found with ID : " + taskId;
        }
    }

    @ShellMethod(key = "delete", value = "Delete the Task")
    public String deleteTask(int taskId) throws IOException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);

        if (!CollectionUtils.isEmpty(tasksList)) {
            Tasks tasks = FileUtil.getTasksById(taskId, tasksList);
            if (Objects.nonNull(tasks)) {
                tasksList.remove(tasks);
                JsonUtil.writeJson(tasksList, taskFile);
                return "Task deleted successfully (ID: " + taskId + ")";
            } else {
                return "No task found with ID : " + taskId;
            }
        } else {
            return "No task found with ID : " + taskId;
        }
    }

    @ShellMethod(key = "mark-in-progress", value = "Mark the Task as In-Progress")
    public String markInProgress(int taskId) throws IOException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);

        if (!CollectionUtils.isEmpty(tasksList)) {
            Tasks tasks = FileUtil.getTasksById(taskId, tasksList);
            if (Objects.nonNull(tasks)) {
                tasks.setStatus("in-progress");
                tasks.setUpdatedAt(LocalDateTime.now());
                JsonUtil.writeJson(tasksList, taskFile);
                return "Updated the status for the Task (ID: " + taskId + ") to In Progress";
            } else {
                return "No task found with ID : " + taskId;
            }
        } else {
            return "No task found with ID : " + taskId;
        }
    }

    @ShellMethod(key = "mark-done", value = "Mark the Task as Done")
    public String markDone(int taskId) throws IOException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);

        if (!CollectionUtils.isEmpty(tasksList)) {
            Tasks tasks = FileUtil.getTasksById(taskId, tasksList);
            if (Objects.nonNull(tasks)) {
                tasks.setStatus("done");
                tasks.setUpdatedAt(LocalDateTime.now());
                JsonUtil.writeJson(tasksList, taskFile);
                return "Updated the status for the Task (ID: " + taskId + ") to Done";
            } else {
                return "No task found with ID : " + taskId;
            }
        } else {
            return "No task found with ID : " + taskId;
        }
    }

    @ShellMethod(key = "list", value = "List all tasks")
    public String listTaskByStatus(@ShellOption(defaultValue = ShellOption.NULL) String status) throws JsonProcessingException {
        File taskFile = FileUtil.getTaskJson();
        List<Tasks> tasksList = JsonUtil.getObject(taskFile, Tasks.class);
        if (!CollectionUtils.isEmpty(tasksList)) {
            if (status != null) {
                List<Tasks> filteredTasks = FileUtil.getTasksByStatus(tasksList, status);
                return JsonUtil.getJson(filteredTasks);
            } else {
                return JsonUtil.getJson(tasksList);
            }
        } else {
            return "No task found";
        }
    }
}
