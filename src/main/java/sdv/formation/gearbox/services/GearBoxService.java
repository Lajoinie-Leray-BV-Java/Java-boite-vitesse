package sdv.formation.gearbox.services;

import org.springframework.stereotype.Service;
import sdv.formation.gearbox.models.GearBox;
import sdv.formation.gearbox.repository.GearRepository;

import java.util.Optional;

@Service
public class GearBoxService {
    private final GearRepository _gearRepository;

    public GearBoxService(GearRepository gearRepository) {
        _gearRepository = gearRepository;
    }

    public Optional<GearBox> getById(Long id) {
        return _gearRepository.findById(id);
    }

    public GearBox createGearBox() {
        GearBox gearBox = new GearBox();
        return _gearRepository.save(gearBox);
    }
}
