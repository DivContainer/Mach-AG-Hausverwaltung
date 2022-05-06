package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.House.HouseStatus;
import de.machag.HouseService.House.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseRepository houseRepository;

    @GetMapping("house/info/id={houseid}")
    @ResponseBody
    public ResponseEntity getHouseById(@PathVariable int houseId) {
        if(houseRepository.findById(houseId).isPresent()) {
            House targetHouse = houseRepository.findById(houseId).get();
            return ResponseEntity.ok(targetHouse.toString());
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @GetMapping("info/address={houseid}")
    @ResponseBody
    public ResponseEntity getHouseByAdressFull(@PathVariable String addressFull) {
        if(houseRepository.findByAddressFull(addressFull) != null) {
            House targetHouse = houseRepository.findById(addressFull).get();
            return ResponseEntity.ok(targetHouse.toString());
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

        try {
            purchasePrice = Double.parseDouble(purchasePriceString);
            rentPerMonth = Double.parseDouble(rentPerMonthString);
        } catch (NumberFormatException numberFormatException) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        HouseStatus houseStatus;

        try {
            houseStatus = HouseStatus.valueOf(statusString);
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

    @DeleteMapping("house/delete/{addressFull}")
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
