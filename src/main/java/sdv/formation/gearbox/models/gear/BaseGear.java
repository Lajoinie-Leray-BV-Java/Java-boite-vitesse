package sdv.formation.gearbox.models.gear;


import jakarta.persistence.*;
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
// Ajout d'une annotation d'héritage pour la stratégie pour que JPA puisse gérer les sous-classes et permettre de correctement changer les vitesses plus tard.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Ajoute une colonne de "discrimination" pour identifier le type de chaque enregistrement dans la table.
// Il était impossible de le mettre sur name dû à la contrainte d'unicité.
@DiscriminatorColumn(name = "gear_type")
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
