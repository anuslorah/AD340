package slorah.com.hw5;

import android.content.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class YarnUnitTestMockito {

    @Mock
    Context mockContext;

    Yarn testYarn;

    @Before
    public void setUp(){
        this.testYarn = new Yarn(145, 50);

        MockitoAnnotations.initMocks(this);
        when(mockContext.getString(R.string.default_yards)).thenReturn("145");
        when(mockContext.getString(R.string.default_grams)).thenReturn("50");
    }

    @Test
    public void yarnReturnsCorrectValues() {
        int yards = testYarn.getYards();
        assertThat(yards).isEqualTo(145);

        int grams = testYarn.getGrams();
        assertThat(grams).isEqualTo(50);

        String gauge = testYarn.findGauge(yards,grams);
        assertThat(gauge).isEqualTo("WORSTED\n4.75 - 5.25 sts per inch (2.5 cm)\n19 -  21 sts per 4\" (10 cm)\nNeedle size: 4.5 - 5mm (7 - 8US)\n" +
                "Hook size: 5 - 5.5mm (H-8 - I-9)\n" +
                "For lace: 5.5 - 6mm (9 - 10US)");
    }

    @Test
    public void yarnFindGaugeBasedOnInput(){
        assertThat(testYarn.findGauge(60, 50)).isEqualTo("BULKY\n2.5 sts per inch (2.5 cm)\n10 sts per 4\" (10 cm)\nNeedle size: 9mm (13US) and up\n" +
                "Hook size: 10mm (N/P-15) and up\n" +
                "For lace: 10 - 12mm (15 - 17US) and up");
    }

    @Test
    public void defaultYarnLoadsDefaultValues(){
        Yarn defaultYarn = new Yarn(mockContext);

        int defaultYards = defaultYarn.getYards();
        int defaultGrams = defaultYarn.getGrams();

        assertThat(defaultYards).isEqualTo(145);
        assertThat(defaultGrams).isEqualTo(50);
        assertThat(defaultYarn.findGauge(defaultYards,defaultGrams)).isEqualTo("WORSTED\n4.75 - 5.25 sts per inch (2.5 cm)\n19 -  21 sts per 4\" (10 cm)\nNeedle size: 4.5 - 5mm (7 - 8US)\n" +
                "Hook size: 5 - 5.5mm (H-8 - I-9)\n" +
                "For lace: 5.5 - 6mm (9 - 10US)");
    }

    @After
    public void tearDown(){

    }
}