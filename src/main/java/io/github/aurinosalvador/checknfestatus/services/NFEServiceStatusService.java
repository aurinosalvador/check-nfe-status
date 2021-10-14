package io.github.aurinosalvador.checknfestatus.services;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import io.github.aurinosalvador.checknfestatus.repositories.NFEServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Aurino Salvador
 */

@RestController
@RequestMapping("/api/v1/nfe")
public class NFEServiceStatusService {

    @Autowired
    NFEServiceStatusRepository nfeRepository;

    @GetMapping("/")
    public List<NFEServiceStatus> getAllNfeServiceStatus() {
        return nfeRepository.findAll();
    }

    @GetMapping("/{id}")
    public NFEServiceStatus getNfeServiceStatusById(@PathVariable Long id) {
        return nfeRepository.findById(id).get();
    }

    @PostMapping("/")
    public NFEServiceStatus saveNfeServiceStatus(@RequestBody NFEServiceStatus nfeServiceStatus) {
        return nfeRepository.save(nfeServiceStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteNfeServiceStatusById(@PathVariable Long id) {
        nfeRepository.deleteById(id);
    }

    @GetMapping("/status/{state}")
    public List<NFEServiceStatus> getLastStatusByState(@PathVariable String state) {
        return nfeRepository.getLastStatusState(state);
    }

    @GetMapping("/status/{state}/{service}")
    public List<NFEServiceStatus> getStatusByService(@PathVariable String state, @PathVariable String service) {
        return nfeRepository.getStatusByStateService(state, service);
    }

    @GetMapping("/status/{state}/{dateStart}/{dateEnd}")
    public List<NFEServiceStatus> getStatusByDate(@PathVariable String state, @PathVariable String dateStart, @PathVariable String dateEnd) {
        Date initialDate = Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(dateStart)));
        Date finishDate = Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(dateEnd)));
        return nfeRepository.findByStateAndUpdatedAtBetweenOrderByUpdatedAt(state, initialDate, finishDate);
    }

    @GetMapping("/status/unavailable")
    public Map<String, Object> getUnavailable() {
        return nfeRepository.getUnavailable();
    }

}
