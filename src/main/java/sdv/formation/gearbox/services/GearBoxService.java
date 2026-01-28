package sdv.formation.gearbox.services;

import org.springframework.stereotype.Service;
import sdv.formation.gearbox.common.GearException;
import sdv.formation.gearbox.enums.GearsEnum;
import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.repository.GearRepository;

import java.util.Optional;

@Service
public class GearBoxService {
    private final GearRepository _gearRepository;

    public GearBoxService(GearRepository gearRepository) {
        _gearRepository = gearRepository;
    }

    public Iterable<GearBox> getAll() {
        return _gearRepository.findAll();
    }

    public Optional<GearBox> getById(Long id) {
        return _gearRepository.findById(id);
    }

    public GearBox createGearBox(GearBox gearBox) {
        return _gearRepository.save(gearBox);
    }

    public GearBox updateGear(Long id, GearsEnum gear) throws GearException {
        Optional<GearBox> gearBoxOpt = _gearRepository.findById(id);

        if (gearBoxOpt.isPresent()) {
            GearBox gearBox = gearBoxOpt.get();
            gearBox.queryToGearByName(gear);
            return _gearRepository.save(gearBox);
        }

        return null;
    }

    public boolean deleteGearBox(Long id) {
        Optional<GearBox> gearBox = _gearRepository.findById(id);
        if (gearBox.isPresent()) {
            _gearRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
