package com.example.firebaseapplication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val firebaseAuth=FirebaseAuth.getInstance()
    private val firestore=FirebaseFirestore.getInstance()
    private val usersCollection=firestore.collection("users")

    fun registerUser(name:String,email:String,rollNo:String,password:String,onResult:(Boolean,String?) ->Unit){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                    val userProfile = UserProfile(name, email, rollNo)
                    usersCollection.document(userId).set(userProfile)
                        .addOnSuccessListener { onResult(true, null) }
                        .addOnFailureListener { onResult(false, it.message) }
                }
                else {
                    onResult(false, task.exception?.message)
                }
                }
            }
    fun loginUser(email: String,rollNo: String,onResult: (Boolean, String?) -> Unit) {
        usersCollection
            .whereEqualTo("email", email)
            .whereEqualTo("rollNo", rollNo)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onResult(false, "Invalid credendtials")
                } else {
                    onResult(true, null)
                }
            }
            .addOnFailureListener { onResult(false, it.message) }
    }
    fun getCurrentUserId():String?=firebaseAuth.currentUser?.uid

    fun isLoggedIn():Boolean=getCurrentUserId() !=null

}