package sdv.formation.gearbox.models.gear;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import sdv.formation.gearbox.common.GearException;

@Entity
@DiscriminatorValue("G4")
public class G4 extends BaseGear {
    public G4(){
        super("G4");
    }

    @Override
    public void toG3() throws GearException {
    }
}
