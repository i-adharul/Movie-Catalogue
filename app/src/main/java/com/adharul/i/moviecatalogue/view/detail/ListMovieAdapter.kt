package com.adharul.i.moviecatalogue.view.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.datalocal.entity.MovieCreditsEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_cast.view.*

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private val castList = ArrayList<MovieCreditsEntity>()

    fun setCastList(list: List<MovieCreditsEntity>)
    {
        castList.clear()
        castList.addAll(list)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder {
        val mView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cast, viewGroup, false)
        return ListViewHolder(mView)
    }

    override fun getItemCount(): Int = castList.size

    override fun onBindViewHolder(listViewHolder: ListViewHolder, position: Int) {
        listViewHolder.bind(castList[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(castDetails: MovieCreditsEntity) {
            with(itemView) {
                tvArtistName.text = castDetails.artistName
                tvCast.text = castDetails.castPosition
                val url = castDetails.photoUrl

                Glide.with(itemView.context)
                    .load(url)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_refresh)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivCast)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(castDetails) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(castDetails: MovieCreditsEntity)
    }
}