package com.adharul.i.moviecatalogue.view.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.adharul.i.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mToolbar = view.findViewById<Toolbar>(R.id.toolbar)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(mToolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.title = "Movie Catalogue"
        (activity as AppCompatActivity).supportActionBar?.elevation = 0f

        val sectionsPagerAdapter = SectionPagerAdapter(
            (activity as AppCompatActivity),
            (activity as AppCompatActivity).supportFragmentManager
        )

        viewPagerHome.adapter = sectionsPagerAdapter
        tabLayoutHome.setupWithViewPager(viewPagerHome)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_icon -> {
                Toast.makeText(context, "searching", Toast.LENGTH_SHORT).show()
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
}