package slorah.com.hw5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testNotEnoughInputProvided(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        activity.findViewById(R.id.my_button).performClick();

        //assertEquals(getString(R.string.gaugeText)).isEqualTo("Please select skein weight.");


    }
}