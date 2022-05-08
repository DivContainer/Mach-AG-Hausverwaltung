package de.machag.HouseService.House;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends CrudRepository<House, String> {

    public Optional<House> findById(int houseId);
    public Optional<House> findByAddressFullIgnoreCase(String addressFull);
    public List<House> findHouseByTennantId(int tennantId);


}
