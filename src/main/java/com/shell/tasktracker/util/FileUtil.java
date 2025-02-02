package com.shell.tasktracker.util;

import com.shell.tasktracker.response.Tasks;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class FileUtil {

    public static File getTaskJson() {
        Path path = Paths.get("tasks.json");
        return path.toFile();
    }

    public static int getMaxId(List<Tasks> tasksList) {
        return tasksList.stream()
                .max(Comparator.comparing(Tasks::getId))
                .map(Tasks::getId)
                .orElse(0);
    }

    public static Tasks getTasksById(int taskId, List<Tasks> tasksList) {
        return tasksList.stream()
                .filter(t -> taskId == t.getId())
                .findFirst()
                .orElse(null);
    }

    public static List<Tasks> getTasksByStatus(List<Tasks> tasksList, String status) {
        return tasksList.stream()
                .filter(t -> t.getStatus().equals(status))
                .toList();
    }
}
