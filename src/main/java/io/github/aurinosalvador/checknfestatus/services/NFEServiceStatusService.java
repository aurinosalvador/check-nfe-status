package io.github.aurinosalvador.checknfestatus.services;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import io.github.aurinosalvador.checknfestatus.repositories.NFEServiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
