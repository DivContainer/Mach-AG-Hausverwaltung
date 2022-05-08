package de.machag.HouseService.RESTController;

import de.machag.HouseService.House.House;
import de.machag.HouseService.House.HouseRepository;
import de.machag.HouseService.Tennant.Tennant;
import de.machag.HouseService.Tennant.TennantRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tennant")
public class TennantController {

    @Autowired
    TennantRepository tennantRepository;

    @Autowired
    HouseRepository houseRepository;

    @GetMapping("info/id={tennantId}")
    @ResponseBody
    public ResponseEntity getTennantById(@PathVariable int tennantId) {
        if(tennantRepository.findById(tennantId).isPresent() && tennantRepository.findById(tennantId).get() != null) {
            Tennant targetTennant = tennantRepository.findById(tennantId).get();
            return ResponseEntity.ok(targetTennant.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/all")
    @ResponseBody
    public ResponseEntity getAllTennants() {
        Iterable<Tennant> allTennants = tennantRepository.findAll();
        if(IterableUtils.size(allTennants) > 0) {
            return ResponseEntity.ok(allTennants.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create/{firstName},{lastName},{emailAddress}")
    @ResponseBody
    public ResponseEntity addTennant(@PathVariable String firstName,
                                             @PathVariable String lastName,
                                             @PathVariable String emailAddress) {

        if(tennantRepository.findByEmailAddressIgnoreCase(emailAddress) == null) {
            Tennant newTennant = new Tennant(firstName, lastName, emailAddress);

            tennantRepository.save(newTennant);

            return ResponseEntity.ok("Added Tennant: " + newTennant.toString());
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @DeleteMapping("{tennantId}")
    @ResponseBody
    public ResponseEntity removeTennant(@PathVariable int tennantId) {
        Optional<Tennant> optionalTennant = tennantRepository.findById(tennantId);
        if(optionalTennant.isPresent()) {
            Tennant targetTennant = optionalTennant.get();

            List<House> rentedHouses = houseRepository.findHouseByTennantId(targetTennant.getId());

            if(rentedHouses.size() > 0) {
                System.out.println("TENNANT HAS RENTED PROPERTIES");
            }

            tennantRepository.delete(targetTennant);
            return ResponseEntity.ok("Removed tennant & all rented properties: " + targetTennant.toString());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
