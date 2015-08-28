package pl.codepot.butelkatr.bottling.model;

/**
 * @author kubukoz
 *         created on 28/08/15.
 */
public class WortCount {
    public WortCount(int wort) {
        this.wort = wort;
    }

    public WortCount() {
    }

    public int getWort() {
        return wort;
    }

    public void setWort(int wort) {
        this.wort = wort;
    }

    private int wort;

}
