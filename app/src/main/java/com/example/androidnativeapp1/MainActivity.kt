package com.example.androidnativeapp1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.app.Dialog
import android.content.Intent
import android.view.Gravity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import java.util.*
import android.os.Handler
import android.widget.ImageView
import com.example.androidnativeapp1.Onboarding1


class MainActivity : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val logoImage: ImageView = findViewById(R.id.logoImage)
        logoImage.setOnClickListener {
            startActivity(Intent(this, Onboarding1::class.java))
        } */
        val handler = Handler()
        val runnable = Runnable {
            startActivity(Intent(this, Onboarding1::class.java))
        }
        val timeoutDuration = 3000L
        handler.postDelayed(runnable, timeoutDuration)


        /*val signupFinishButton: Button = findViewById(R.id.signupFinishButton)
        signupFinishButton.setOnClickListener {
            viewSavedTranslationDialog()
        } */

        /*val translationCardView1: CardView = findViewById(R.id.cardView1)
        translationCardView1.setOnClickListener {
            viewSavedTranslationDialog()
        } */

        /*val signupFinishButton: ImageView = findViewById(R.id.leftDrawerIcon)
        signupFinishButton.setOnClickListener {
            displayLeftDrawer()
        } */

        /*val signupFinishButton: Button = findViewById(R.id.signupFinishButton)
        signupFinishButton.setOnClickListener {
            signupFinishButtonFunc()
        } */

        /*val signupFinishButton: Button = findViewById(R.id.createNewPasswordButton)
        signupFinishButton.setOnClickListener {
            createNewPasswordButtonFunc()
        } */


        //Built in left drawer with many issues
        /*drawerLayout = findViewById(R.id.drawerLayout)
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
        }  */

        /*val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    setContentView(R.layout.home)
                    true
                }
                R.id.camera_tab -> {
                    setContentView(R.layout.scan_qr_code)
                    true
                }
                R.id.learn_tab -> {
                    setContentView(R.layout.list_of_lessons)
                    true
                }
                R.id.chat_tab -> {
                    setContentView(R.layout.chat_initial_page)
                    true
                }
                else -> false
            }
        }*/
    }


    //Built in left drawer with many issues
    /*override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    } */

    private fun signupFinishButtonFunc() {
        lateinit var signupFinishButton: Button
        signupFinishButton = findViewById(R.id.signupFinishButton)
        signupFinishButton.setOnClickListener {
            signupFinishDialog()
        }
    }
    private fun createNewPasswordButtonFunc() {
        lateinit var resetForgotPasswordButton: Button
        resetForgotPasswordButton = findViewById(R.id.createNewPasswordButton)
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

    private fun displayLeftDrawer() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.left_drawer_layout)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.apply {
            gravity = Gravity.TOP or Gravity.START
        }
        window?.attributes = layoutParams
        dialog.show()

        val closeLeftDrawerButton = dialog.findViewById<TextView>(R.id.closeLeftDrawerButton)
        closeLeftDrawerButton.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun quizCompletedDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.quiz_completed_layout)

        val quizCompletedHomeButton = dialog.findViewById<Button>(R.id.quizCompletedHomeButton)
        quizCompletedHomeButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.home)
        }
        dialog.show()
    }

    private fun sessionCompletedDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.session_completed_layout)

        val quizCompletedHomeButton = dialog.findViewById<Button>(R.id.sessionCompletedHomeButton)
        quizCompletedHomeButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.home)
        }
        dialog.show()
    }

    private fun cancelQuizDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.cancel_quiz_layout)

        val continueQuizButton = dialog.findViewById<Button>(R.id.continueQuizButton)
        continueQuizButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.ongoing_quiz)
        }

        val cancelQuizButton = dialog.findViewById<Button>(R.id.cancelQuizButton)
        cancelQuizButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.list_of_quizzes)
        }
        dialog.show()
    }

    private fun cancelSessionDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.cancel_session_layout)

        val continueSessionButton = dialog.findViewById<Button>(R.id.continueSessionButton)
        continueSessionButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.session_start_view)
        }

        val cancelSessionButton = dialog.findViewById<Button>(R.id.cancelSessionButton)
        cancelSessionButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.scan_qr_code)
        }
        dialog.show()
    }

    private fun confirmLogoutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.confirm_logout_layout)

        val confirmLogoutButton = dialog.findViewById<Button>(R.id.confirmLogoutButton)
        confirmLogoutButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.login)
        }

        val cancelLogoutButton = dialog.findViewById<Button>(R.id.cancelLogoutButton)
        cancelLogoutButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showChatOptionsDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.chat_options_layout)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.apply {
            gravity = Gravity.TOP or Gravity.END
        }

        window?.attributes = layoutParams
        dialog.show()
    }

    private fun inviteFriendDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.invite_friend_layout)

        val inviteButton = dialog.findViewById<Button>(R.id.inviteButton)
        inviteButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.contact_list)
        }

        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dialog.dismiss()
            setContentView(R.layout.contact_list)
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

    private fun viewSavedTranslationDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.view_translation_layout)

        val closeSavedTranslationButton = dialog.findViewById<TextView>(R.id.closeSavedTranslationButton)
        closeSavedTranslationButton.setOnClickListener {
            dialog.dismiss()
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