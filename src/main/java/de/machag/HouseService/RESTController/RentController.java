package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.House.HouseRepository;
import de.machag.HouseService.House.HouseStatus;
import de.machag.HouseService.Tennant.Tennant;
import de.machag.HouseService.Tennant.TennantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    TennantRepository tennantRepository;

    @PostMapping("/assign/{houseId},{tennantId},force={forceBool}")
    public ResponseEntity assignHouseToTennant(@PathVariable int houseId,
                                               @PathVariable int tennantId,
                                               @PathVariable boolean forceBool) {

        Optional<House> optionalHouse = houseRepository.findById(houseId);
        Optional<Tennant> optionalTennant = tennantRepository.findById(tennantId);

        if(optionalHouse.isPresent() && optionalTennant.isPresent()) {
            House targetHouse = optionalHouse.get();
            Tennant targetTennant = optionalTennant.get();

            if(targetHouse.getTennantId() == 0) {
                targetHouse.setTennantId(targetTennant.getId());
                targetHouse.setStatus(HouseStatus.RENTED);
                houseRepository.save(targetHouse);
                return ResponseEntity.ok("Assigned new tennant to property");
            } else {
                if(forceBool == true) {
                    targetHouse.setTennantId(targetTennant.getId());
                    targetHouse.setStatus(HouseStatus.RENTED);
                    houseRepository.save(targetHouse);
                    return ResponseEntity.ok("Force-Assigned new tennant to property and overwritten the old tennant");
                }
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{houseId}")
    public ResponseEntity removeTennantFromHouse(@PathVariable int houseId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if(optionalHouse.isPresent()) {
            House targetHouse = optionalHouse.get();
            if(targetHouse.getTennantId() != 0) {
                targetHouse.setTennantId(0);
                targetHouse.setStatus(HouseStatus.FREE);
                houseRepository.save(targetHouse);
                return ResponseEntity.ok("Removed tennant from house");
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
