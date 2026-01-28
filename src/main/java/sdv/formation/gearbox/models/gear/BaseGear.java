package sdv.formation.gearbox.models.gear;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.enums.GearsEnum;

@Getter
@Entity
@Data
@NoArgsConstructor
public class BaseGear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public BaseGear(String name) {
        this.name = name;
    }

    public void toGR() throws GearException {
        throw new GearException(name, "GR");
    }

    public void toGN() throws GearException {
        throw new GearException(name, "GN");
    }

    public void toG1() throws GearException {
        throw new GearException(name, "G1");
    }

    public void toG2() throws GearException {
        throw new GearException(name, "G2");
    }

    public void toG3() throws GearException {
        throw new GearException(name, "G3");
    }

    public void toG4() throws GearException {
        throw new GearException(name, "G4");
    }

}
