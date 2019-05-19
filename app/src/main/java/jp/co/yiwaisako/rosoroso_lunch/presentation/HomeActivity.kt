package jp.co.yiwaisako.rosoroso_lunch.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        restaurant_list.setOnClickListener {
            StationListActivity.createIntent(this).apply { startActivity(this) }
        }
    }

    private fun test() {
        firestore = FirebaseFirestore.getInstance()

        val user: MutableMap<String, String> = mutableMapOf()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = "1815"

        // Add a new document with a generated ID
        firestore.collection("users")
            .add(user)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.d(
                    "MainActivity",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            })
            .addOnFailureListener(OnFailureListener { e -> Log.w("MainActivity", "Error adding document", e) })
    }
}
