package com.adharul.i.moviecatalogue.view.tvshowgrid

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
import kotlinx.android.synthetic.main.fragment_tv_show_grid.*

class TvShowGridFragment : Fragment() {

    private lateinit var gridFilmAdapter: GridAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mDetailFragment: DetailFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this, factory
        )[HomeViewModel::class.java]

        gridFilmAdapter = GridAdapter()

        viewModel.getPopularTvShowList().observe(viewLifecycleOwner, { latestTvShowList ->
            showLoading(true)

            if (latestTvShowList != null) {
                gridFilmAdapter.setFilmList(latestTvShowList)
                showLoading(false)
            }
            gridFilmAdapter.notifyDataSetChanged()
            showLoading(false)
        })

        rvTvShow.layoutManager = GridLayoutManager(context, 2)
        rvTvShow.adapter = gridFilmAdapter

        gridFilmAdapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FilmEntity) {
                //Bundle
                val detailBundle = Bundle()
                detailBundle.putString(DetailFragment.EXTRA_FILM_ID, data.filmId)
                detailBundle.putInt(DetailFragment.EXTRA_FRAGMENT, 2)
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
            progressBarTvShowGrid.visibility = View.VISIBLE
        } else {
            progressBarTvShowGrid.visibility = View.GONE
        }
    }
}