package com.musicPlayer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var uri: String = ""

class UserRepository {
    val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("users")
            .child(uri)
            .child("audio")

    @Volatile
    private var INSTANCE: UserRepository? = null

    fun getInstance(): UserRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadTasks(taskList: MutableLiveData<List<Audio>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _taskLists: List<Audio> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Audio::class.java)!!

                    }
//                    val _taskLists: List<Tasks> = snapshot.children.map {
//                        val task2 = it.getValue(Tasks::class.java)!!
//                        val taskId = it.key
//                        return taskId
//                    }
                    var list: List<Audio> = emptyList()
                    _taskLists.forEach{
                        /*if (it.status != "Завершено"){
                            list += it
                        }*/
                    }
                    taskList.value = list
                } catch (e: Exception) {
                    Log.e("ERROR", e.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}