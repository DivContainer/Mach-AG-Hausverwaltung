package de.machag.HouseService.RESTController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tennant")
public class TennantController {

    @GetMapping("info/id={tennantId}")
    @ResponseBody
    public ResponseEntity getTennantById(@PathVariable String tennantId) {
        /**
        if(tennantRepository.findById(tennantId).isPresent() && tennantRepository.findById(tennantId).get() != null) {
            Tennant targetTennant = tennantRepository.findById(tennantId).get();
            return ResponseEntity.ok(targetTennant.toString());
        }
         **/
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("info/all")
    @ResponseBody
    public ResponseEntity getAllTennants() {
        /**
        List<Tennant> allTennants = tennantRepository.findAll();
        if(allTennants.size() > 0) {
            return ResponseEntity.ok(utilities.fancyPrintAll(allTennants));
        }
         **/
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
