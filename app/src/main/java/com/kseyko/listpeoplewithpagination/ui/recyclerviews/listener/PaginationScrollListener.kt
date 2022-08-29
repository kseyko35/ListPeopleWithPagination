package com.kseyko.listpeoplewithpagination.ui.recyclerviews.listener

import androidx.recyclerview.widget.RecyclerView


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      28,August,2022      ║
╚════════════════════════════╝
 */
abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val offset = recyclerView.computeVerticalScrollOffset()
            val extent = recyclerView.computeVerticalScrollExtent()
            val range = recyclerView.computeVerticalScrollRange()
            if (offset + extent >= range - offset ) {
                onScrollLimit()
            }
        }
    }

    abstract fun onScrollLimit()
}