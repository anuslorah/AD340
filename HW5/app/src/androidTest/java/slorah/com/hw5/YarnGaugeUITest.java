package slorah.com.hw5;

import android.support.test.espresso.action.TypeTextAction;
import android.support.test.runner.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class YarnGaugeUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestYarnGaugeGeneration(){

        //set grams value on spinner
        onView(withId(R.id.gram_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("50"))).perform(click());

        //set yards value in editText
        onView(withId(R.id.my_yards)).perform(typeText("60"));

        //click button
        onView(withId(R.id.my_button)).perform(click());

        //check value
        onView(withId(R.id.gaugeText)).check(matches(withText("BULKY\n2.5 sts per inch (2.5 cm)\n10 sts per 4\" (10 cm)\nNeedle size: 9mm (13US) and up\n" +
                "Hook size: 10mm (N/P-15) and up\n" +
                "For lace: 10 - 12mm (15 - 17US) and up")));
    }

    //no gram values selected & no yards values selected
    @Test
    public void TestEmptyInputs(){
        //set no grams value on spinner
        onView(withId(R.id.gram_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("choose skein weight"))).perform(click());

        //set no yards value in editText
        onView(withId(R.id.my_yards)).perform(typeText(""));

        //click button
        onView(withId(R.id.my_button)).perform(click());

        //check value
        onView(withId(R.id.gaugeText)).check(matches(withText("Please select skein weight.")));
    }

    //no gram values selected & yards values selected
    @Test
    public void TestEmptyGramInput(){
        //set no grams value on spinner
        onView(withId(R.id.gram_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("choose skein weight"))).perform(click());

        //set no yards value in editText
        onView(withId(R.id.my_yards)).perform(typeText("60"));

        //click button
        onView(withId(R.id.my_button)).perform(click());

        //check value
        onView(withId(R.id.gaugeText)).check(matches(withText("Please select skein weight.")));
    }

    //gram values selected & no yards values selected
    @Test
    public void TestEmptyYardsInput(){
        //set no grams value on spinner
        onView(withId(R.id.gram_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("50"))).perform(click());

        //set no yards value in editText
        onView(withId(R.id.my_yards)).perform(typeText(""));

        //click button
        onView(withId(R.id.my_button)).perform(click());

        //check value
        onView(withId(R.id.gaugeText)).check(matches(withText("Please select skein length.")));
    }
}
