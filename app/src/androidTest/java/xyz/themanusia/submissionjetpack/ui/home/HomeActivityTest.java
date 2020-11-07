package xyz.themanusia.submissionjetpack.ui.home;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {
    private final List<MovieEntity> dummyMovie = DataDummy.generateMovieData();
    private final List<TvEntity> dummyTv = DataDummy.generateTvData();

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    //Memastikan rvMovie tampil
    //Scroll rvMovie ke data terakhir
    @Test
    public void loadMovie() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    //Klik TabLayout dengan teks TV SHOW
    //Memastikan rvTv tampil
    //Scroll rvTv ke data terakhir
    @Test
    public void loadTv() {
        onView(withText("TV SHOW")).perform(click());
        onView(withId(R.id.rvTv)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTv)).perform(RecyclerViewActions.scrollToPosition(dummyTv.size()));
    }

    //Klik data pertama pada rvMovie
    //Memastikan tvTitle tampil benar
    //Memastikan tvOverview tampil benar
    //Memastikan tvYear tampil benar
    //Memastikan tvRating tampil benar
    @Test
    public void loadDetail() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()));
        onView(withId(R.id.tvYear)).check(matches(withText(dummyMovie.get(0).getYear())));
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()));
        onView(withId(R.id.tvRating)).check(matches(withText(dummyMovie.get(0).getRating())));
    }

}