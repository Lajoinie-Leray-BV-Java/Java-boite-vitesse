package sdv.formation.gearbox.models.gear;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("GN")
public class GN extends BaseGear {
    public GN() {
        super("GN");
    }

    @Override
    public void toGR() throws GearException {
    }

    @Override
    public void toG1() throws GearException {
    }

    @Override
    public void toG3() throws GearException {
    }
}
