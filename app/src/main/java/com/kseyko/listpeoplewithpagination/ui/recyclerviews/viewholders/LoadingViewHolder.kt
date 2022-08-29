package com.kseyko.listpeoplewithpagination.ui.recyclerviews.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.kseyko.listpeoplewithpagination.databinding.ItemProgressBinding

/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      28,August,2022        ║
╚════════════════════════════╝
 */
class LoadingViewHolder(private val mBinding: ItemProgressBinding) :
    RecyclerView.ViewHolder(mBinding.root) {

    fun bind(isVisible: Boolean) {
        mBinding.isVisible = isVisible
    }
}