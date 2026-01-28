package sdv.formation.gearbox.sdv.formation.java;

import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.common.GearException;

public class Main {
    public static void main(String[] args) {
         GearBox bVitesse = new GearBox();

        System.out.println(bVitesse.getSName());

        try {
            System.out.println("Changement de vitesse");

            bVitesse.queryG1();
            bVitesse.queryG2();
        } catch (GearException e) {
            System.out.println(e);
        }
    }
}