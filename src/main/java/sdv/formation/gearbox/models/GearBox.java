package sdv.formation.gearbox.models;

import jakarta.persistence.*;
import lombok.Data;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.models.gear.*;

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

    public String getSName() {
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
