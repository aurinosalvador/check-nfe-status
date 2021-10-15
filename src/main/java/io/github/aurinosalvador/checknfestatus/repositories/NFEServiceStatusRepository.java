package io.github.aurinosalvador.checknfestatus.repositories;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Aurino Salvador
 */

public interface NFEServiceStatusRepository extends JpaRepository<NFEServiceStatus, Long> {

    @Query(value = "select state from sys_nfe_status group by 1", nativeQuery = true)
    List<String> getStates();

    @Query(value = "select * from sys_nfe_status where updated_at = (select max(updated_at) from sys_nfe_status)", nativeQuery = true)
    List<NFEServiceStatus> getAllLastStatus();

    @Query(value = "select * from sys_nfe_status where state = ?1 and updated_at = " +
            "(select max(updated_at) from sys_nfe_status where state = ?1)", nativeQuery = true)
    List<NFEServiceStatus> getLastStatusState(String state);

    @Query(value = "select * from sys_nfe_status where state = ?1 and service = ?2 and updated_at = " +
            "(select max(updated_at) from sys_nfe_status where state = ?1)", nativeQuery = true)
    List<NFEServiceStatus> getStatusByStateService(String state, String service);

    List<NFEServiceStatus> findByStateAndUpdatedAtBetweenOrderByUpdatedAt(String state, Date initialDate, Date finishDate);

    @Query(value = "select state, count(service) from sys_nfe_status where " +
            "status = 'red' and status != 'Don''t match!' " +
            "group  by 1 order by 2 desc limit 1", nativeQuery = true)
    Map<String, Object> getUnavailable();

}
