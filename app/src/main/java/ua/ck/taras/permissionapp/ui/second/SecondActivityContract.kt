package ua.ck.taras.permissionapp.ui.second

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract : ActivityResultContract<Int, String?>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        // input value == id and init in MainActivity when ".launch(10)"
        Log.i("SecondActivityContract", "createIntent: ${input ?: -1}")
        return Intent(context, SecondActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getIntExtra(SecondActivity.INTENT_KEY_CUSTOM_CONTRACT_ID, -1)
        return if (resultCode == Activity.RESULT_OK) data.toString()
        else null
    }
}