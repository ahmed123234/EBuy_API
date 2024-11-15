package com.example.simpleapi.services.country;

import com.example.simpleapi.Repositories.country.CountryRepository;
import com.example.simpleapi.entities.country.Country;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImplementation {
    private final CountryRepository countryRepository;

    public void addCountry(Country country) {
         log.info("inside country service class country is {}", country.getName());
        countryRepository.save(country);
    }

    public void addCountries(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public void getCountry(Integer countryId, HttpServletResponse response) {

       Optional<Country> country = countryRepository.findById(countryId);

       // check if country is present
       Response.checkObjectAvailability(response, country,
               "No such country with the specified id %s", countryId);

    }

    public void deleteCountry(HttpServletResponse response, Integer countryId) {

       boolean isPresent =  Response.checkObjectAvailability(response, countryRepository.findById(countryId),
                "Country is deleted successfully",
                "Country is not found, therefore it can't be deleted",
                countryId);

       if (isPresent) {
           countryRepository.deleteById(countryId);
           log.info("Country with Id {} is successfully deleted", countryId);
       } else {
           log.error("Country with Id {} not found!", countryId);
       }

    }


    public void updateCountry(Integer countryId, String name, HttpServletResponse response) {

        boolean isPresent = Response.checkObjectAvailability(response,
                countryRepository.findById(countryId),
                "Country is updated successfully",
                "Country is not found, therefore it can't be updated",
                countryId);

        if(isPresent) {
            countryRepository.updateCountry(name, countryId);
            log.info("Country with Id {} is successfully updated", countryId);

        } else {
            log.error("Country with Id {} not found!", countryId);
        }
    }
}
