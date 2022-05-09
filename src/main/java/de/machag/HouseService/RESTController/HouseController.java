package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.House.HouseRepository;
import de.machag.HouseService.House.HouseStatus;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseRepository houseRepository;

    @GetMapping("info/id={houseId}")
    @ResponseBody
    public ResponseEntity getHouseById(@PathVariable int houseId) {
        if(houseRepository.findById(houseId).isPresent() && houseRepository.findById(houseId).get() != null) {
            House targetHouse = houseRepository.findById(houseId).get();
            return ResponseEntity.ok(targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/address={addressFull}")
    @ResponseBody
    public ResponseEntity getHouseByAdressFull(@PathVariable String addressFull) {
        Optional<House> optionalHouse = houseRepository.findByAddressFullIgnoreCase(addressFull);
        if(optionalHouse.isPresent()) {
            House targetHouse = optionalHouse.get();
            return ResponseEntity.ok(targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/all")
    @ResponseBody
    public ResponseEntity getAllHouses() {
        Iterable<House> allHouses = houseRepository.findAll();
        if(IterableUtils.size(allHouses) > 0) {
            return ResponseEntity.ok(allHouses.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{addressFull},{purchasePrice},{rentPerMonth},{statusString}")
    @ResponseBody
    public ResponseEntity createHouse(@PathVariable String addressFull,
                                        @PathVariable double purchasePrice,
                                        @PathVariable double rentPerMonth,
                                        @PathVariable String statusString) {

        HouseStatus houseStatus;

        try {
            houseStatus = HouseStatus.parseString(statusString);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(houseRepository.findByAddressFullIgnoreCase(addressFull).isPresent()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        House newHouse = new House(
                addressFull,
                purchasePrice,
                rentPerMonth,
                houseStatus,
                0,
                new Timestamp(new Date().getTime())
        );

        houseRepository.save(newHouse);

        return ResponseEntity.ok("Added house: " + newHouse.toString());
    }

    @DeleteMapping("{addressFull}")
    @ResponseBody
    public ResponseEntity deleteHouseByAddressFull(@PathVariable String addressFull) {
        Optional<House> optionalHouse = houseRepository.findByAddressFullIgnoreCase(addressFull);
        if(optionalHouse.isPresent()) {
            House targetHouse = optionalHouse.get();
            if(targetHouse != null) {
                houseRepository.delete(targetHouse);
                return ResponseEntity.ok("Removed house: \n" + targetHouse.toString());
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
