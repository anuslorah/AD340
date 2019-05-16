package slorah.com.hw5;

import android.content.Context;

public class Yarn {

    private String gauge;
    private int yards;
    private int grams;
    private String message;

    public Yarn(int yards, int grams) {

        //check invariants, add default value
        if (yards <= 0) {
            message = "Please enter yards";
            gauge = message;
            this.yards = 200;
        } else {
            this.yards = yards;
        }

        if(grams != 50 && grams != 100) {
            this.grams = 100;
            message = "please choose your skein size";
            gauge = message;
        } else {
            this.grams = grams;
        }
    }

    //default Yarn
    public Yarn(Context context) {
        this.yards = Integer.parseInt(context.getString(R.string.default_yards));
        this.grams = Integer.parseInt(context.getString(R.string.default_grams));
    }


    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public String findGauge(int yards, int grams) {

        if(grams == 50 && yards <= 60 && yards > 0 || grams == 100 &&  yards <= 120) {
            gauge = "BULKY\n2.5 sts per inch (2.5 cm)\n10 sts per 4\" (10 cm)\nNeedle size: 9mm (13US) and up\n" +
                    "Hook size: 10mm (N/P-15) and up\n" +
                    "For lace: 10 - 12mm (15 - 17US) and up";
        } else if (grams == 50 && yards > 60 && yards <= 85 || grams == 100 &&  yards > 120 && yards <= 170){
            gauge = "CHUNKY\n2.75 - 3.75 sts per inch (2.5 cm)\n11 - 15 sts per 4\" (10 cm)\nNeedle size: 6 - 8mm (10 - 11US)\n" +
                    "Hook size: 6mm (J-10) and up\n" +
                    "For lace: 9 - 10mm (13 - 15US)";
        } else if (grams == 50 && yards > 85 && yards <= 100 || grams == 100 &&  yards > 170 && yards <= 200){
            gauge = "ARAN\n4 - 4.5 sts per inch (2.5 cm)\n16 - 18 sts per 4\" (10 cm)\nNeedle size: 5 - 5.5mm (8 - 9US)"+
                    "Hook size: 5 - 6mm (H-8 - J-10)\n" +
                    "For lace: 6 - 8mm (10 - 11US)";
        } else if (grams == 50 && yards > 100 && yards <= 150 || grams == 100 &&  yards > 200 && yards <= 250){
            gauge = "WORSTED\n4.75 - 5.25 sts per inch (2.5 cm)\n19 -  21 sts per 4\" (10 cm)\nNeedle size: 4.5 - 5mm (7 - 8US)\n" +
                    "Hook size: 5 - 5.5mm (H-8 - I-9)\n" +
                    "For lace: 5.5 - 6mm (9 - 10US)";
        } else if (grams == 50 && yards > 150 && yards <= 180 || grams == 100 &&  yards > 250 && yards <= 360){
            gauge = "DK or LIGHT WORSTED\n5.5 - 6 sts per inch (2.5 cm)\n22 - 24 sts per 4\" (10 cm)\nNeedle size: 3.75 - 4mm (5 - 6US)\n" +
                    "Hook size: 4 - 4.5mm (G-6 - 7)\n" +
                    "For lace work: 4.5 - 5.5mm (7 - 9US)";
        } else if (grams == 50 && yards > 180 && yards <= 230 || grams == 100 &&  yards > 360 && yards <= 460){
            gauge = "FINGERING or SOCK\n7 - 8 sts per inch (2.5 cm)\n28 - 32 sts per 4\" (10 cm)\nNeedle size: 2.25 - 3.25mm (1 - 3US)\n" +
                    "Hook size: 2.75 - 3.5mm (C-2 - E-4)\n" +
                    "For lace work: 3.5 - 4mm (4 - 6US)";
        } else if (grams == 50 && yards > 230 || grams == 100 &&  yards > 460){
            gauge = "LACE\n8.5 sts per inch (2.5 cm)\n34 sts per 4\" (10 cm) or less\nNeedle size: 2.25mm (1US) or smaller\n" +
                    "Hook size: 2.25 (B-1)\n" +
                    "For lace work: 2.75 - 3.5mm (2 - 4US)";
        } else {
            gauge = "Please check your inputs";
        }

        return gauge;
    }//end findGauge()

}