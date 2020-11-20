package com.adharul.i.moviecatalogue.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.datalocal.entity.FilmEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_grid_film.view.*

class GridAdapter : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    private var filmList = ArrayList<FilmEntity>()

    fun setFilmList(list: List<FilmEntity>?) {
        if (list == null) return
        filmList.clear()
        filmList.addAll(list)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_grid_film, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filmEntity: FilmEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(filmEntity.posterUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_refresh)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivItemPoster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(filmEntity) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FilmEntity)
    }
}