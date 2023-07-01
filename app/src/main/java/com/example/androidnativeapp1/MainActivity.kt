package com.example.androidnativeapp1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.CheckBox
import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item1 -> {
                }
                R.id.menu_item2 -> {
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    true
                }
                R.id.camera_tab -> {
                    true
                }
                R.id.learn_tab -> {
                    true
                }
                R.id.chat_tab -> {
                    true
                }
                else -> false
            }
        }

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun signupFinishButtonFunc() {
        lateinit var signupFinishButton: Button
        signupFinishButton = findViewById(R.id.signupFinishButton)

        signupFinishButton.setOnClickListener {
            signupFinishDialog()
        }
    }
    private fun resetForgotPasswordButtonFunc() {
        lateinit var resetForgotPasswordButton: Button
        resetForgotPasswordButton = findViewById(R.id.resetForgotPasswordButton)

        resetForgotPasswordButton.setOnClickListener {
            resetPasswordFinishDialog()
        }
    }

    private fun signupFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.signup_finish_layout)

        val signupFinishLoginButton = dialog.findViewById<Button>(R.id.signupFinishLoginButton)
        signupFinishLoginButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.login)
        }

        dialog.show()
    }

    private fun resetPasswordFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.reset_password_layout)

        val resetPasswordLoginButton = dialog.findViewById<Button>(R.id.resetPasswordLoginButton)
        resetPasswordLoginButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.login)
        }

        dialog.show()
    }



    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                // Do something with the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                selectedDateTextView.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}