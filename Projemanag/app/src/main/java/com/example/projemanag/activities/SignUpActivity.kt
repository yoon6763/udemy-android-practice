package com.example.projemanag.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignUpBinding
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionBar()


    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarSignUpActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding.toolbarSignUpActivity.setNavigationOnClickListener { onBackPressed() }
        binding.btnSignUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        binding.run {
            val name: String = etName.text.toString().trim { it <= ' ' }
            val email: String = etEmail.text.toString().trim { it <= ' ' }
            val password: String = etPassword.text.toString().trim { it <= ' ' }

            if (validateForm(name, email, password)) {
                showProgressDialog(getString(R.string.please_wait))
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val fireBaseUser: FirebaseUser = task.result!!.user!!
                            val registeredEmail = fireBaseUser.email!!
                            val user = User(fireBaseUser.uid, name, registeredEmail)

                            FirestoreClass().registerUser(this@SignUpActivity, user)
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                task.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    fun userRegisteredSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }


    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter a password")
                false
            }
            else -> {
                true
            }
        }
    }
}