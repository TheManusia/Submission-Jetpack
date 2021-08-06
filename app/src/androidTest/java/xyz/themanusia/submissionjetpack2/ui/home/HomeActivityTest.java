package xyz.themanusia.submissionjetpack2.ui.home;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xyz.themanusia.submissionjetpack2.R;
import xyz.themanusia.submissionjetpack2.utils.EspressoIdlingResource;

public class HomeActivityTest {

    @Before
    public void setup() {
        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    //Memastikan rvMovie tampil
    //Scroll rvMovie ke data terakhir
    @Test
    public void loadMovie() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition(10));
    }

    //Klik TabLayout dengan teks TV SHOW
    //Memastikan rvTv tampil
    //Scroll rvTv ke data terakhir
    @Test
    public void loadTv() {
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rvTv)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTv)).perform(RecyclerViewActions.scrollToPosition(10));
    }

    //Klik data pertama pada rvMovie
    //Memastikan tvTitle tampil
    //Memastikan tvOverview tampil
    //Memastikan tvYear tampil
    //Memastikan tvRating tampil
    @Test
    public void loadDetail() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()));
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()));
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()));
    }

}