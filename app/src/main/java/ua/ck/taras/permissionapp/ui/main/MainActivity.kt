package ua.ck.taras.permissionapp.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import ua.ck.taras.permissionapp.R
import ua.ck.taras.permissionapp.common.showToast
import ua.ck.taras.permissionapp.model.Task
import ua.ck.taras.permissionapp.model.User
import ua.ck.taras.permissionapp.ui.second.SecondActivityContract
import ua.ck.taras.permissionapp.ui.second.SecondActivity

class MainActivity : AppCompatActivity() {

    private val openSecondActivityGeneral =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getIntExtra(SecondActivity.INTENT_KEY_ID, -1)
                showToast("openSecondActivityGeneral: Activity.RESULT_OK. Data: ${data.toString()}")
            } else {
                showToast("openSecondActivityGeneral: Activity.NOT_OK")
            }
        }

    private val openSecondActivityCustomContract =
        registerForActivityResult(SecondActivityContract()) { result ->
            if (result != null) {
                showToast("actionClickActivityButton: $result ")
            } else {
                showToast("actionClickActivityButton: result = NULL ")
            }
        }

    private val requestPermissionGeneral =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> showToast("One Permission granted")
                false -> showToast("One Permission NOT granted")
            }
        }

    private val requestMultiplePermissionGeneral =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionList ->
            permissionList.entries.forEach { map ->
                Log.i("MainActivity", "${map.key}: ${map.value} ")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        // Open Second Activity - General
        activityButtonOpenSecondActivityGeneral.setOnClickListener {
            openSecondActivityGeneral.launch(
                Intent(this, SecondActivity::class.java)
            )
        }

        // Open Second Activity - CustomContract
        activityButtonOpenSecondActivityCustomContract.setOnClickListener {
            openSecondActivityCustomContract.launch(10)
        }

        // Request Permission - General
        activityButtonRequestPermissionGeneral.setOnClickListener {
            requestPermissionGeneral.launch(Manifest.permission.RECORD_AUDIO)
        }

        // Request Multiple Permission - General
        activityButtonRequestMultiplePermissionGeneral.setOnClickListener {
            requestMultiplePermissionGeneral.launch(
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA
                )
            )
        }

        // Send Parcel List
        activityButtonSendParcelList.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(
                SecondActivity.INTENT_PARCEL_USER_LIST, arrayListOf(
                    User(
                        id = 1,
                        name = "User_1",
                        taskList = listOf(Task(id = 1, title = "Task_1")),
                        surname = "Surname_1",
                        age = 1,
                        salary = "Salary_1"
                    ), User(
                        id = 2,
                        name = "User_2",
                        taskList = listOf(Task(id = 1, title = "Task_2")),
                        surname = "Surname_2",
                        age = 2,
                        salary = "Salary_2"
                    ), User(
                        id = 3,
                        name = "User_3",
                        taskList = listOf(Task(id = 1, title = "Task_3")),
                        surname = "Surname_3",
                        age = 3,
                        salary = "Salary_3"
                    )
                )
            )
            startActivity(intent)
        }
    }
}