package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.Tennant.Tennant;
import de.machag.HouseService.Tennant.TennantRepository;
import de.machag.HouseService.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tennant")
public class TennantController {

    @Autowired
    TennantRepository tennantRepository;

    @Autowired
    Util utilities;

    @GetMapping("info/id={tennantId}")
    @ResponseBody
    public ResponseEntity getTennantById(@PathVariable String tennantId) {
        if(tennantRepository.findById(tennantId).isPresent() && tennantRepository.findById(tennantId).get() != null) {
            Tennant targetTennant = tennantRepository.findById(tennantId).get();
            return ResponseEntity.ok(targetTennant.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/all")
    @ResponseBody
    public ResponseEntity getAllTennants() {
        List<Tennant> allTennants = tennantRepository.findAll();
        if(allTennants.size() > 0) {
            return ResponseEntity.ok(utilities.fancyPrintAll(allTennants));
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
