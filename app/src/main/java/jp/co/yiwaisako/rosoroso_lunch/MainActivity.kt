package jp.co.yiwaisako.rosoroso_lunch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        review.setOnClickListener {

        }

        restaurant_list.setOnClickListener {
            StationListActivity.createIntent(this).apply { startActivity(this) }
        }
    }

    private fun test() {
        mFirestore = FirebaseFirestore.getInstance()

        val user: MutableMap<String, String> = mutableMapOf()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = "1815"

        // Add a new document with a generated ID
        mFirestore.collection("users")
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
