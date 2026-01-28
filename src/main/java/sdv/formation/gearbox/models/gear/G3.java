package sdv.formation.gearbox.models.gear;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("G3")
public class G3 extends BaseGear {
    public G3(){
        super("G3");
    }

    @Override
    public void toG2() throws GearException {
    }

    @Override
    public void toG4() throws GearException {
    }
}
