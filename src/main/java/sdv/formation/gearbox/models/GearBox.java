package sdv.formation.gearbox.models;

import jakarta.persistence.*;
import lombok.Data;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.enums.GearsEnum;
import sdv.formation.gearbox.models.gear.*;

import java.lang.reflect.Constructor;
import java.util.Locale;

@Entity
@Data
public class GearBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "g_current_id")
    private BaseGear gCurrent;

    public GearBox() {
        gCurrent = new GN();
    }

    public static GearBox createFromGEnum(GearsEnum gName) {
        String cName = String.format("sdv.formation.gearbox.models.gear.%s", gName);

        try {
            Class<?> cls = Class.forName(cName);
            Constructor<?> ctr = cls.getConstructor();
            Object obj = ctr.newInstance();
            GearBox gb = new GearBox();
            gb.gCurrent = (BaseGear) obj;
            return gb;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void queryToGearByName(GearsEnum gear) throws GearException {
        switch (gear) {
            case GearsEnum.GR -> queryGR();
            case GearsEnum.GN -> queryGN();
            case GearsEnum.G1 -> queryG1();
            case GearsEnum.G2 -> queryG2();
            case GearsEnum.G3 -> queryG3();
            case GearsEnum.G4 -> queryG4();
            default -> throw new GearException(gCurrent.getName(), gear.name());
        }
    }

    public String getGName() {
        return gCurrent.getName();
    }

    public void queryGR() throws GearException {
        gCurrent.toGR();
        gCurrent = new GR();
    }

    public void queryGN() throws GearException {
        gCurrent.toGN();
        gCurrent = new GN();
    }

    public void queryG1() throws GearException {
        gCurrent.toG1();
        gCurrent = new G1();
    }

    public void queryG2() throws GearException {
        gCurrent.toG2();
        gCurrent = new G2();
    }

    public void queryG3() throws GearException {
        gCurrent.toG3();
        gCurrent = new G3();
    }

    public void queryG4() throws GearException {
        gCurrent.toG4();
        gCurrent = new G4();
    }
}
