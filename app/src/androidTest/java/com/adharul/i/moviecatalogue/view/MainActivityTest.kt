package com.adharul.i.moviecatalogue.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.utils.EspressoIdlingResource
import com.adharul.i.moviecatalogue.utils.OriginalData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    // Update the OriginalData.class before ui testing OR
    // change "position" in loadDetailMovie() and loadDetailTvShow()

    private val movieDetails = OriginalData.generateMovieDetails()
    private val tvShowDetails = OriginalData.generateTvShowDetails()

    companion object {
        private const val LAST_POSITION = 19
    }

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rvMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(LAST_POSITION))
    }

    @Test
    fun loadDetailMovies() {
        Espresso.onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.tvFilmName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvFilmName))
            .check(ViewAssertions.matches(withText(movieDetails.movieTitle)))
        Espresso.onView(withId(R.id.tvStatus))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvStatus))
            .check(ViewAssertions.matches(withText(movieDetails.status)))
        Espresso.onView(withId(R.id.tvLanguage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvLanguage))
            .check(ViewAssertions.matches(withText(movieDetails.language)))
        Espresso.onView(withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(withText(movieDetails.runtime)))
        Espresso.onView(withId(R.id.tvOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvOverview))
            .check(ViewAssertions.matches(withText(movieDetails.overview)))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(LAST_POSITION))
    }

    @Test
    fun loadDetailTvShows() {
        Espresso.onView(withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.tvFilmName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvFilmName))
            .check(ViewAssertions.matches(withText(tvShowDetails.tvShowName)))
        Espresso.onView(withId(R.id.tvStatus))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvStatus))
            .check(ViewAssertions.matches(withText(tvShowDetails.status)))
        Espresso.onView(withId(R.id.tvLanguage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvLanguage))
            .check(ViewAssertions.matches(withText(tvShowDetails.language)))
        Espresso.onView(withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(withText(tvShowDetails.epsRuntime)))
        Espresso.onView(withId(R.id.tvOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvOverview))
            .check(ViewAssertions.matches(withText(tvShowDetails.overview)))
    }
}