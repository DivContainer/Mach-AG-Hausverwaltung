package de.machag.HouseService.House;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HouseRepository extends MongoRepository<House, String> {

    public Optional<House> findById(int id);
    public House findByAddressFull(String addressFull);

}
