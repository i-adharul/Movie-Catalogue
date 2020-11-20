package com.adharul.i.moviecatalogue.view.home

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.view.moviegrid.MovieGridFragment
import com.adharul.i.moviecatalogue.view.tvshowgrid.TvShowGridFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(R.string.tab_text_movies, R.string.tab_text_tv_shows)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieGridFragment()
            1 -> fragment = TvShowGridFragment()
        }
        return fragment as Fragment
    }

    override fun getCount() = 2

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }

}