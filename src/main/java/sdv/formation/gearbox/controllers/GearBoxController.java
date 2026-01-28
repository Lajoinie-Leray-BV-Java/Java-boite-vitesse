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
@RequestMapping("/gearBox")
public class GearBoxController {
    private final GearBoxService _gearBoxService;

    /**
    * Initialise le controlleur avec le service de boîte de vitesses.
    * @param gearBoxService Le service de boîte de vitesses à utiliser.
    */
    public GearBoxController(GearBoxService gearBoxService) {
        _gearBoxService = gearBoxService;
    }

    /**
    * Récupère toutes les boîtes de vitesses.
    * @return Une réponse HTTP contenant la liste des boîtes de vitesses.
    */
    @GetMapping()
    public ResponseEntity<Iterable<GearBox>> getAllGearBoxes() {
        Iterable<GearBox> gearBoxes = _gearBoxService.getAll();
        return ResponseEntity.ok(gearBoxes);
    }

    /**
    * Récupère une boîte de vitesses par son identifiant.
    * @param id L'identifiant de la boîte de vitesses à récupérer.
    * @return Une réponse HTTP contenant la boîte de vitesses si trouvée, sinon une réponse 404.
    */
    @GetMapping("/{id}")
    public ResponseEntity<GearBox> getGearBox(@PathVariable("id") Long id) {
        GearBox gearBox = _gearBoxService.getById(id).orElse(null);
        if (gearBox == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(gearBox);
    }

    /**
    * Crée une nouvelle boîte de vitesses.
    * @param createGearBoxRequest La requête de création de boîte de vitesses (optionnelle).
    * @return Une réponse HTTP contenant la boîte de vitesses créée.
    */
    @PostMapping()
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

    /**
    * Met à jour la boîte de vitesses en changeant de vitesse.
    * @param id L'identifiant de la boîte de vitesses à mettre à jour.
    * @param targetGear La vitesse cible à laquelle passer.
    * @return Une réponse HTTP contenant la boîte de vitesses mise à jour ou un message d'erreur.
    */
    @PutMapping("/{id}/to/{targetGear}")
    public ResponseEntity<?> swapGear(@PathVariable("id") Long id, @PathVariable("targetGear") GearsEnum targetGear) {
        try {
            GearBox gearBox = _gearBoxService.updateGear(id, targetGear);
            if (gearBox == null) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(gearBox);
        } catch (GearException gE) {
            return ResponseEntity.badRequest().body(gE.getMessage());
        }
    }

    /**
     * Supprime une boîte de vitesses par son identifiant.
     * Nous n'avons pas utiliser @DeleteMapping car cela envoyait une erreur 405 Method Not Supported .
     * @param id L'identifiant de la boîte de vitesses à supprimer.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGearBox(@PathVariable("id") Long id) {
        boolean isDeleted = _gearBoxService.deleteGearBox(id);
        if (!isDeleted) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
