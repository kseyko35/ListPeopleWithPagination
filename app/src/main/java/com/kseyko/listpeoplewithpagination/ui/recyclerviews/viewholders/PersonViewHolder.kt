package com.kseyko.listpeoplewithpagination.ui.recyclerviews.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.kseyko.listpeoplewithpagination.databinding.ItemPersonBinding
import com.kseyko.listpeoplewithpagination.source.Person

/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      28,August,2022        ║
╚════════════════════════════╝
 */
class PersonViewHolder(private val mBinding: ItemPersonBinding) :
    RecyclerView.ViewHolder(mBinding.root) {

    fun bind(item: Person) {
        mBinding.person = item
    }
}