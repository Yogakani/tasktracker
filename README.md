Clone this project.
Execute mvn clean install -DskipTests
Run the app using java -jar target/<Jar-Name>.jar
Once application launched, Shell will be opened.

Run help to list all the available commands.

Task Manager
       add: Add new Task
       update: Update existing Task
       mark-done: Mark the Task as Done
       list: List all tasks
       mark-in-progress: Mark the Task as In-Progress
       delete: Delete the Task

shell:>add "buy groceries"
Task added successfully (ID: )1
shell:>add "buy meat"
Task added successfully (ID: 2)
shell:>add "Play FC25"
Task added successfully (ID: 3)
shell:>add "Watch Jailer"
Task added successfully (ID: 4)

shell:>update 1 "Buy groceries and cook dinner"
Task updated successfully (ID: 1)

shell:>delete 2
Task deleted successfully (ID: 2)

shell:>mark-in-progress 4
Updated the status for the Task (ID: 4) to In Progress

shell:>mark-done 3
Updated the status for the Task (ID: 3) to Done

shell:>list
[ {
  "id" : 1,
  "description" : "buy groceries and cook dinner",
  "status" : "todo",
  "createdAt" : "2025-02-02T18:13:28",
  "updatedAt" : "2025-02-02T18:14:02"
}, {
  "id" : 3,
  "description" : "Play FC25",
  "status" : "done",
  "createdAt" : "2025-02-02T18:57:46",
  "updatedAt" : "2025-02-02T19:27:47"
}, {
  "id" : 4,
  "description" : "Watch Jailer",
  "status" : "in-progress",
  "createdAt" : "2025-02-02T19:04:48",
  "updatedAt" : "2025-02-02T19:20:16"
} ]

shell:>list done
[ {
  "id" : 3,
  "description" : "Play FC25",
  "status" : "done",
  "createdAt" : "2025-02-02T18:57:46",
  "updatedAt" : "2025-02-02T19:27:47"
} ]

shell:>list todo
[ {
  "id" : 1,
  "description" : "buy groceries and cook dinner",
  "status" : "todo",
  "createdAt" : "2025-02-02T18:13:28",
  "updatedAt" : "2025-02-02T18:14:02"
} ]

shell:>list in-progress
[ {
  "id" : 4,
  "description" : "Watch Jailer",
  "status" : "in-progress",
  "createdAt" : "2025-02-02T19:04:48",
  "updatedAt" : "2025-02-02T19:20:16"
} ]






