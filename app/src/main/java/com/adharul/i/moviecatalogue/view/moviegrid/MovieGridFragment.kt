package com.adharul.i.moviecatalogue.view.moviegrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.datalocal.entity.FilmEntity
import com.adharul.i.moviecatalogue.view.detail.DetailFragment
import com.adharul.i.moviecatalogue.view.home.GridAdapter
import com.adharul.i.moviecatalogue.view.home.HomeViewModel
import com.adharul.i.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_grid.*

class MovieGridFragment : Fragment() {

    private lateinit var gridFilmAdapter: GridAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mDetailFragment: DetailFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this, factory
        )[HomeViewModel::class.java]

        gridFilmAdapter = GridAdapter()

        viewModel.getPopularMovieList().observe(viewLifecycleOwner, { popularMovieList ->
            showLoading(true)

            if (popularMovieList != null) {
                gridFilmAdapter.setFilmList(popularMovieList)
                showLoading(false)
            }
            gridFilmAdapter.notifyDataSetChanged()
            showLoading(false)
        })

        rvMovie.layoutManager = GridLayoutManager(context, 2)
        rvMovie.adapter = gridFilmAdapter

        gridFilmAdapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FilmEntity) {
                //Bundle
                val detailBundle = Bundle()
                detailBundle.putString(DetailFragment.EXTRA_FILM_ID, data.filmId)
                detailBundle.putInt(DetailFragment.EXTRA_FRAGMENT, 1)
                mDetailFragment = DetailFragment(activity as AppCompatActivity)
                mDetailFragment.arguments = detailBundle

                //Fragment Manager
                mFragmentManager = (activity as AppCompatActivity).supportFragmentManager
                val detailFragment =
                    mFragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
                if (detailFragment !is DetailFragment) {
                    mFragmentManager
                        .beginTransaction()
                        .add(
                            R.id.frame_container,
                            mDetailFragment,
                            DetailFragment::class.java.simpleName
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
        })
    }

    private fun showLoading(state: Boolean = false) {
        if (state) {
            progressBarMovieGrid.visibility = View.VISIBLE
        } else {
            progressBarMovieGrid.visibility = View.GONE
        }
    }
}