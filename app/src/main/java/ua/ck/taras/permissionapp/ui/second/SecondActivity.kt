package ua.ck.taras.permissionapp.ui.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ua.ck.taras.permissionapp.R
import ua.ck.taras.permissionapp.model.User

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        // Get UserList
        getParcelUserList()
    }

    override fun onBackPressed() {
        // Back Result
        setBackResult()
        super.onBackPressed()
    }

    private fun setBackResult() {
        val intent = Intent().apply {
            putExtra(INTENT_KEY_ID, 100)
            putExtra(INTENT_KEY_CUSTOM_CONTRACT_ID, 200)
        }
        setResult(Activity.RESULT_OK, intent)
    }

    private fun getParcelUserList() {
        val userList = intent.getParcelableArrayListExtra<User>(INTENT_PARCEL_USER_LIST)
        userList?.forEach { user ->
            Log.i("SecondActivity", "user: ${user.name}")
        }
    }

    companion object {
        const val INTENT_KEY_ID = "id"
        const val INTENT_KEY_CUSTOM_CONTRACT_ID = "id_custom_contract"

        const val INTENT_PARCEL_USER_LIST = "parcel_user_list"
    }
}