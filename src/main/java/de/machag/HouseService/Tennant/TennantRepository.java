package de.machag.HouseService.Tennant;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TennantRepository extends MongoRepository<Tennant, String> {

    public Optional<Tennant> findById(String id);
    public Tennant findByFirstNameIgnoreCase(String firstName);
    public Tennant findByLastNameIgnoreCase(String lastName);
    public Tennant findByEmailAddressIgnoreCase(String emailAddress);

}
