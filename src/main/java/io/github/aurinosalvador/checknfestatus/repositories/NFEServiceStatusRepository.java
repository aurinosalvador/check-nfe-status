package io.github.aurinosalvador.checknfestatus.repositories;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aurino Salvador
 */

public interface NFEServiceStatusRepository extends JpaRepository<NFEServiceStatus, Long> {

}
