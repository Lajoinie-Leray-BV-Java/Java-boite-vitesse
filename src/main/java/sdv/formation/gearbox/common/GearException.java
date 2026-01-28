package sdv.formation.gearbox.common;

public class GearException extends Exception {

    public GearException(String gSrc, String gTarget) {
        super(String.format("Cannot swap gear from %s to %s", gSrc, gTarget));
    }
}
