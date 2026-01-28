package sdv.formation.gearbox.services;

import org.springframework.stereotype.Service;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.enums.GearsEnum;
import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.repository.GearBoxRepository;

import java.util.Optional;

@Service
public class GearBoxService {
    private final GearBoxRepository _gearBoxRepository;

    /**
     * Constructeur de la classe GearBoxService.
     * @param gearBoxRepository Le référentiel de boîtes de vitesses.
     */
    public GearBoxService(GearBoxRepository gearBoxRepository) {
        _gearBoxRepository = gearBoxRepository;
    }

    /**
     * Récupère toutes les boîtes de vitesses.
     * @return Une itérable contenant toutes les boîtes de vitesses.
     */
    public Iterable<GearBox> getAll() {
        return _gearBoxRepository.findAll();
    }

    /**
     * Récupère une boîte de vitesses par son identifiant.
     * @param id L'identifiant de la boîte de vitesses.
     * @return La boîte de vitesses correspondante, ou une valeur vide si elle n'existe pas.
     */
    public Optional<GearBox> getById(Long id) {
        return _gearBoxRepository.findById(id);
    }

    /**
     * Crée une nouvelle boîte de vitesses.
     * @param gearBox La boîte de vitesses à créer.
     * @return La boîte de vitesses créée.
     */
    public GearBox createGearBox(GearBox gearBox) {
        return _gearBoxRepository.save(gearBox);
    }

    /**
     * Met à jour la boîte de vitesses avec le nouvel état de la vitesse.
     * @param id L'identifiant de la boîte de vitesses à mettre à jour.
     * @param gear La nouvelle vitesse à appliquer.
     * @return La boîte de vitesses mise à jour, ou null si elle n'existe pas.
     * @throws GearException Si la transition de vitesse n'est pas valide.
     */
    public GearBox updateGear(Long id, GearsEnum gear) throws GearException {
        Optional<GearBox> gearBoxOpt = _gearBoxRepository.findById(id);

        if (gearBoxOpt.isPresent()) {
            GearBox gearBox = gearBoxOpt.get();
            gearBox.queryToGearByName(gear);
            return _gearBoxRepository.save(gearBox);
        }

        return null;
    }

    /**
     * Supprime une boîte de vitesses par son identifiant.
     * @param id L'identifiant de la boîte de vitesses à supprimer.
     * @return true si la boîte de vitesses a été supprimée, false sinon.
     */
    public boolean deleteGearBox(Long id) {
        Optional<GearBox> gearBox = _gearBoxRepository.findById(id);
        if (gearBox.isPresent()) {
            _gearBoxRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
