package io.github.aurinosalvador.checknfestatus.repositories;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Aurino Salvador
 */

public interface NFEServiceStatusRepository extends JpaRepository<NFEServiceStatus, Long> {

    @Query(value = "select * from sys_nfe_status where state = ?1 and updated_at = " +
            "(select max(updated_at) from sys_nfe_status where state = ?1)", nativeQuery = true)
    List<NFEServiceStatus> getLastStatusState(String state);

}
