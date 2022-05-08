package de.machag.HouseService.Tennant;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TennantRepository extends CrudRepository<Tennant, Integer> {

    public Optional<Tennant> findById(int id);
    public Tennant findByFirstNameIgnoreCase(String firstName);
    public Tennant findByLastNameIgnoreCase(String lastName);
    public Tennant findByEmailAddressIgnoreCase(String emailAddress);

}
