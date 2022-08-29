package com.kseyko.listpeoplewithpagination.extensions

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kseyko.listpeoplewithpagination.ui.recyclerviews.adapters.PersonListAdapter


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      28,August,2022      ║
╚════════════════════════════╝
 */


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}