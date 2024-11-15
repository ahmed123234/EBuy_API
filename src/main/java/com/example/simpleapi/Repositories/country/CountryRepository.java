package com.example.simpleapi.Repositories.country;

import com.example.simpleapi.entities.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Transactional
    @Modifying
    @Query("update Country c set c.name = ?1 where c.id = ?2")
    void updateCountry(String name, Integer id);

}
