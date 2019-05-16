package slorah.com.hw5;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class YarnRoboElectricTest {

    @Test
    public void defaultYarnYardsWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.getYards()).isEqualTo(145);
    }

    @Test
    public void defaultYarnGramsWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.getGrams()).isEqualTo(50);
    }

    @Test
    public void defaultYarnGaugeWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.findGauge(145, 50)).isEqualTo("WORSTED\n4.75 - 5.25 sts per inch (2.5 cm)\n19 -  21 sts per 4\" (10 cm)\nNeedle size: 4.5 - 5mm (7 - 8US)\n" +
                "Hook size: 5 - 5.5mm (H-8 - I-9)\n" +
                "For lace: 5.5 - 6mm (9 - 10US)");
    }

    @Test
    public void noYardInputYarnGaugeWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.findGauge(0, 50)).isEqualTo("Please check your inputs");
    }

    @Test
    public void negativeYardInputYarnGaugeWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.findGauge(-1, 50)).isEqualTo("Please check your inputs");
    }

    @Test
    public void noGramInputYarnGaugeWithRobolectric() {
        Context roboContext = ApplicationProvider.getApplicationContext();
        Yarn defaultYarn = new Yarn(roboContext);
        assertThat(defaultYarn.findGauge(1, 0)).isEqualTo("Please check your inputs");
    }

}
