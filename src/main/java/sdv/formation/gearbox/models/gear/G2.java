package sdv.formation.gearbox.models.gear;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("G2")
public class G2 extends BaseGear {
    public G2() {
        super("G2");
    }

    @Override
    public void toGN() throws GearException {
    }
}
