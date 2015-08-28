package pl.codepot.butelkatr.bottling.model;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */
public class Bottle{
    public Bottle(int wort) {
        this.wort = wort;
    }

    public Bottle() {
    }

    public int getWort() {
        return wort;
    }

    public void setWort(int wort) {
        this.wort = wort;
    }

    private int wort;

}
