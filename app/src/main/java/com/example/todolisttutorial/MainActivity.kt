package com.example.todolisttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolisttutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        // Handle the click event to delete marked tasks
        binding.deleteSelectedButton.setOnClickListener {
            taskViewModel.deleteMarkedTasks()
        }
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setCompleted(taskItem)
    }

    override fun onTaskSelectedForDeletion(taskItem: TaskItem, isSelected: Boolean) {
        // Handle task selection for deletion here
        // You can update your ViewModel or perform other actions
        taskViewModel.toggleTaskSelection(taskItem, isSelected)
    }
}
