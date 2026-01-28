package sdv.formation.gearbox.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.enums.GearsEnum;
import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.requests.CreateGearBoxRequest;
import sdv.formation.gearbox.services.GearBoxService;

import java.util.Optional;

@RestController
@RequestMapping
public class GearBoxController {
    private final GearBoxService _gearBoxService;
    private final String _path = "/gearBox";

    public GearBoxController(GearBoxService gearBoxService) {
        _gearBoxService = gearBoxService;
    }

    @GetMapping(_path)
    public ResponseEntity<Iterable<GearBox>> getAllGearBoxes() {
        Iterable<GearBox> gearBoxes = _gearBoxService.getAll();
        return ResponseEntity.ok(gearBoxes);
    }

    @GetMapping(_path + "/{id}")
    public ResponseEntity<GearBox> getGearBox(@PathVariable("id") Long id) {
        GearBox gearBox = _gearBoxService.getById(id).orElse(null);
        if (gearBox == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(gearBox);
    }

    @PostMapping(_path)
    public ResponseEntity<GearBox> createGearBox(@RequestBody Optional<CreateGearBoxRequest> createGearBoxRequest) {
        if (createGearBoxRequest.isPresent()) {
            try {
                GearsEnum gearName = createGearBoxRequest.get().getGearName();
                GearBox createdGBFromName = GearBox.createFromGEnum(gearName);

                GearBox savedGearBox = _gearBoxService.createGearBox(createdGBFromName);
                return ResponseEntity.ok(savedGearBox);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }

        GearBox createdGearBox = _gearBoxService.createGearBox(new GearBox());
        return ResponseEntity.ok(createdGearBox);
    }

    @PutMapping(_path + "/{id}/to/{targetGear}")
    public ResponseEntity<?> swapGear(@PathVariable("id") Long id, @PathVariable("targetGear") GearsEnum targetGear) {
        try {
            GearBox gearBox = _gearBoxService.updateGear(id, targetGear);
            return ResponseEntity.ok(gearBox);
        } catch (GearException gE) {
            return ResponseEntity.badRequest().body(gE.getMessage());
        }
    }

    @DeleteMapping(_path + "/{id} ")
    public ResponseEntity<Void> deleteGearBox(@PathVariable("id") Long id) {
        _gearBoxService.deleteGearBox(id);
        return ResponseEntity.noContent().build();
    }
}
