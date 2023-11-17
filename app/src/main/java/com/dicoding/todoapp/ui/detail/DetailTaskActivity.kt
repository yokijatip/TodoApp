package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val context = this
        val viewModelFactory = ViewModelFactory.getInstance(context)
        detailTaskViewModel = ViewModelProvider(this, viewModelFactory)[DetailTaskViewModel::class.java]

        //TODO 11 : Show detail task and implement delete action Done✅

        val taskId = intent.getIntExtra(TASK_ID, 0)
        detailTaskViewModel.setTaskId(taskId)
        detailTaskViewModel.task.observe(this) { task ->

            if (task != null ){
                findViewById<TextView>(R.id.detail_ed_title).text = task.title
                findViewById<TextView>(R.id.detail_ed_description).text = task.description
                findViewById<TextView>(R.id.detail_ed_due_date).text = DateConverter.convertMillisToString(task.dueDateMillis)
            }

        }

        val deleteButton: Button = findViewById(R.id.btn_delete_task)
        deleteButton.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }

    }
}