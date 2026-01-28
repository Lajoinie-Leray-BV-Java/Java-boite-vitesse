package sdv.formation.gearbox.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.services.GearBoxService;

@RestController
@RequestMapping
public class GearBoxController {
    private final GearBoxService _gearBoxService;
    private final String _path = "/gearBox";

    public GearBoxController(GearBoxService gearBoxService) {
        _gearBoxService = gearBoxService;
    }

    @GetMapping(_path + "/{id}")
    public ResponseEntity<GearBox> getGearBox(@PathVariable Long id) {
        GearBox gearBox = _gearBoxService.getById(id).orElse(null);
        if (gearBox == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(gearBox);
    }

    @PostMapping(_path)
    public ResponseEntity<GearBox> createGearBox() {
        GearBox createdGearBox = _gearBoxService.createGearBox();
        return ResponseEntity.ok(createdGearBox);
    }
}
