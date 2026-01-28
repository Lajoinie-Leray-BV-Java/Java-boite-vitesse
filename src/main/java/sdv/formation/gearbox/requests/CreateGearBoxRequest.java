package sdv.formation.gearbox.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sdv.formation.gearbox.enums.GearsEnum;

@AllArgsConstructor
@Getter
public class CreateGearBoxRequest {
    public GearsEnum gearName;
}
