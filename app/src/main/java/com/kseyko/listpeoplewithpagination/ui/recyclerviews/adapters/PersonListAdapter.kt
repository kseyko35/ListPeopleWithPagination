package com.kseyko.listpeoplewithpagination.ui.recyclerviews.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kseyko.listpeoplewithpagination.R
import com.kseyko.listpeoplewithpagination.databinding.ItemPersonBinding
import com.kseyko.listpeoplewithpagination.databinding.ItemProgressBinding
import com.kseyko.listpeoplewithpagination.source.Person
import com.kseyko.listpeoplewithpagination.ui.recyclerviews.viewholders.LoadingViewHolder
import com.kseyko.listpeoplewithpagination.ui.recyclerviews.viewholders.PersonViewHolder


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║       28,August,2022       ║
╚════════════════════════════╝
 */
class PersonListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded: Boolean =false
    private var personList: ArrayList<Person> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM -> {
                val binding: ItemPersonBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_person, parent, false)
                viewHolder = PersonViewHolder(binding)
            }
            LOADING -> {
                val binding: ItemProgressBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_progress, parent, false)
                viewHolder = LoadingViewHolder(binding)
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = personList.size

    fun setItems(items: ArrayList<Person>) {
        this.personList.clear()
        this.personList.addAll(items)
        notifyDataSetChanged()
    }

    fun addLoadingFooter() {
        isLoadingAdded = false
        add(Person(-1,""))
    }

    fun removeLoadingFooter() {
        isLoadingAdded = true
        val position: Int = personList.size - 1
        personList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(movie: Person) {
        personList.add(movie)
        notifyItemInserted(personList.size - 1)
    }



    companion object {
        private const val LOADING = 0
        private const val ITEM = 1
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> {
                val movieViewHolder = holder as PersonViewHolder
                movieViewHolder.bind(personList[position])
            }
            LOADING -> {
                val loadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.bind(isLoadingAdded)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == personList.size - 1 && isLoadingAdded) LOADING else ITEM
    }



}