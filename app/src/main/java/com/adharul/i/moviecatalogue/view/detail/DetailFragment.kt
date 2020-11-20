package com.adharul.i.moviecatalogue.view.detail

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment(private val activity: Activity) : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var listMovieAdapter: ListMovieAdapter
    private lateinit var listTvShowAdapter: ListTvShowAdapter

    companion object {
        const val EXTRA_FILM_ID = "extra_film_id"
        const val EXTRA_FRAGMENT = "extra_fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mToolbar = view.findViewById<Toolbar>(R.id.toolbar)
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(mToolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.title = "Detail"
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.elevation = 0f

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this, factory
        )[DetailViewModel::class.java]

        listMovieAdapter = ListMovieAdapter()
        listTvShowAdapter = ListTvShowAdapter()

        //Bundle
        arguments?.let {
            val filmId = it.getString(EXTRA_FILM_ID).toString()
            val fragment = it.getInt(EXTRA_FRAGMENT, 1)

            viewModel.setSelectedFilm(filmId, fragment)

            when (fragment) {
                1 -> getMovieDetails()
                else -> getTvShowDetails()
            }
        }
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails().observe(viewLifecycleOwner,
            { movieEntity ->
                showLoading(true)
                if (movieEntity != null) {
                    Glide.with(this)
                        .load(movieEntity.posterUrl)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_refresh)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(ivPoster)

                    tvFilmName.text = nullStringConversion(movieEntity.movieTitle)
                    tvStatus.text = nullStringConversion(movieEntity.status)
                    tvLanguage.text = nullStringConversion(movieEntity.language)
                    tvRuntime.text = nullStringConversion(movieEntity.runtime)
                    tvOverview.text = nullStringConversion(movieEntity.overview)

                    listMovieAdapter.notifyDataSetChanged()
                    showLoading(false)
                }
            }
        )

        viewModel.getMovieCredits().observe(viewLifecycleOwner,
            {castList ->
                showLoading(true)

                if (castList != null) {
                    listMovieAdapter.setCastList(castList)
                    showLoading(false)
                }
                listMovieAdapter.notifyDataSetChanged()
                showLoading(false)
            })

        rvCast.layoutManager = LinearLayoutManager(context)
        rvCast.adapter = listMovieAdapter
        rvCast.setHasFixedSize(true)
    }

    private fun getTvShowDetails() {
        viewModel.getTvShowDetails().observe(viewLifecycleOwner,
            { tvShowEntity ->
                showLoading(true)
                if (tvShowEntity != null) {
                    Glide.with(this)
                        .load(tvShowEntity.posterUrl)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_refresh)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(ivPoster)

                    tvFilmName.text = nullStringConversion(tvShowEntity.tvShowName)
                    tvStatus.text = nullStringConversion(tvShowEntity.status)
                    tvLanguage.text = nullStringConversion(tvShowEntity.language)
                    tvRuntime.text = nullStringConversion(tvShowEntity.epsRuntime)
                    tvOverview.text = nullStringConversion(tvShowEntity.overview)
                    showLoading(false)
                }
            }
        )

        viewModel.getTvShowCredits().observe(viewLifecycleOwner,
            {castList ->
                showLoading(true)

                if (castList != null) {
                    listTvShowAdapter.setCastList(castList)
                    showLoading(false)
                }
                listMovieAdapter.notifyDataSetChanged()
                showLoading(false)
            })

        rvCast.layoutManager = LinearLayoutManager(context)
        rvCast.adapter = listTvShowAdapter
        rvCast.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                parentFragmentManager.popBackStack()
                return true
            }
            R.id.favorite_icon -> {
                Toast.makeText(context, "favorite", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    private fun nullStringConversion(text: String?): String {
        return text ?: "Not Set"
    }

    private fun showLoading(state: Boolean = false) {
        if (state) {
            progressBarMovieGrid.visibility = View.VISIBLE
        } else {
            progressBarMovieGrid.visibility = View.GONE
        }
    }
}