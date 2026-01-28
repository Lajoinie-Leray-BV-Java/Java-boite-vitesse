package sdv.formation.gearbox.models.gear;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("GR")
public class GR extends BaseGear {

    public GR() {
        super("GR");
    }

    @Override
    public void toGN() throws GearException {
    }
}
