package com.danp1925.todolist.ui.navigation

import com.danp1925.todolist.ui.navigation.NavRoutes.TaskDetailArgs.TaskId

object NavRoutes {

    const val TasksList = "tasks_list"

    const val NewTask = "new_task"

    const val TaskDetail = "task_detail/{$TaskId}"

    object TaskDetailArgs{
        const val TaskId = "taskId"
    }

}