package com.example.firebaseapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseapplication.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val rollNo = binding.rollNoEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.registerUser(name, email, rollNo, password) { success, message ->
                if (success) {
                    Log.d("RegisterActivity", "Registration successful.")
                    Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    Log.e("RegisterActivity", "Registration failed: $message")
                }
            }
        }
        binding.loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}