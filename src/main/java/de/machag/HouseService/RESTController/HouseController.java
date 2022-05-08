package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.House.HouseStatus;
import de.machag.HouseService.House.HouseRepository;
import de.machag.HouseService.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    Util utilities;

    @GetMapping("info/id={houseId}")
    @ResponseBody
    public ResponseEntity getHouseById(@PathVariable String houseId) {
        if(houseRepository.findById(houseId).isPresent() && houseRepository.findById(houseId).get() != null) {
            House targetHouse = houseRepository.findById(houseId).get();
            return ResponseEntity.ok(targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/address={addressFull}")
    @ResponseBody
    public ResponseEntity getHouseByAdressFull(@PathVariable String addressFull) {
        if(houseRepository.findByAddressFull(addressFull) != null) {
            House targetHouse = houseRepository.findById(addressFull).get();
            return ResponseEntity.ok(targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/all")
    @ResponseBody
    public ResponseEntity getAllHouses() {
        List<House> allHouses = houseRepository.findAll();
        if(allHouses.size() > 0) {
            return ResponseEntity.ok(utilities.fancyPrintAll(allHouses));
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create/{addressFull},{purchasePriceString},{rentPerMonthString},{statusString}")
    @ResponseBody
    public ResponseEntity createAccount(@PathVariable String addressFull,
                                        @PathVariable String purchasePriceString,
                                        @PathVariable String rentPerMonthString,
                                        @PathVariable String statusString) {

        double purchasePrice;
        double rentPerMonth;
        HouseStatus houseStatus;

        try {
            purchasePrice = Double.parseDouble(purchasePriceString);
            rentPerMonth = Double.parseDouble(rentPerMonthString);
            houseStatus = HouseStatus.parseString(statusString);
        } catch (NumberFormatException numberFormatException) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(houseRepository.findByAddressFull(addressFull) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        House newHouse = new House(
                UUID.randomUUID().toString(),
                addressFull,
                purchasePrice,
                rentPerMonth,
                houseStatus,
                null
        );

        houseRepository.save(newHouse);

        return ResponseEntity.ok("Added house: " + newHouse.toString());
    }

    @DeleteMapping("{addressFull}")
    @ResponseBody
    public ResponseEntity deleteHouseByAddressFull(@PathVariable String addressFull) {
        House targetHouse = houseRepository.findByAddressFull(addressFull);
        if(targetHouse != null) {
            houseRepository.delete(targetHouse);
            return ResponseEntity.ok("Removed house: \n" + targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
