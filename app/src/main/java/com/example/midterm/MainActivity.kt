package com.example.midterm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchUsers()
    }


    private fun fetchUsers() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val userList = findViewById<ListView>(R.id.user_list)
        progressBar.visibility = View.VISIBLE


        lifecycleScope.launch {
            try {
                val retrofit by lazy { RetrofitHelper.getInstance() }
                val newsApiService = retrofit.create(UserApi::class.java)
                val response = newsApiService.getUsers()

                var layoutManager = LinearLayoutManager(this@MainActivity)
                layoutManager = LinearLayoutManager(this@MainActivity)
//                userList.adapter = dataLoaderViewModel.loadUsersList()
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Failed to fetch users: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    class UserListAdapter(private val userList: List<User>) :
        RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.activity_main,
                parent,
                false
            )
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(userList[position])
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
            private val emailTextView: TextView = itemView.findViewById(R.id.email_text_view)

            fun bind(user: User) {
                emailTextView.text = "${user.email}"
                nameTextView.text = "${user.results}"


            }
        }
    }
}