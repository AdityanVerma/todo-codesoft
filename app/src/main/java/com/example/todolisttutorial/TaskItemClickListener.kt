package com.example.todolisttutorial

interface TaskItemClickListener
{
    fun onTaskSelectedForDeletion(taskItem: TaskItem, isSelected: Boolean)
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}
