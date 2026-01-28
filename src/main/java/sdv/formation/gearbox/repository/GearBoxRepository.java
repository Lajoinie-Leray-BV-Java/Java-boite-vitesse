package sdv.formation.gearbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdv.formation.gearbox.models.GearBox;

public interface GearBoxRepository extends JpaRepository<GearBox, Long> {
}
