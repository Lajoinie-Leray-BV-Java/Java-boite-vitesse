package sdv.formation.gearbox.models.gear;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("G1")
public class G1 extends BaseGear {

    public G1() {
        super("G1");
    }

    @Override
    public void toGR() throws GearException {
    }

    @Override
    public void toGN() throws GearException {
    }
}
