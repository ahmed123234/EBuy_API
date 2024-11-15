package com.example.simpleapi.controllers.countrycontroller;

import com.example.simpleapi.entities.country.Country;
import com.example.simpleapi.services.country.CountryServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
//@PreAuthorize("")
@RequiredArgsConstructor
public class CountryController {

    private final CountryServiceImplementation countryService;

    @PostMapping("/add")
    public ResponseEntity<Response> addCountry(@RequestBody() Country country) {
        countryService.addCountry(new Country(country.getName()));

        return ResponseEntity.status(201).body(Response.builder()
                .message(String.format("Country %s is added successfully", country.getName())).build());
    }


    @PostMapping("/addAll")
    public ResponseEntity<?> addCountry(@RequestBody List<Country> countries) {
        countryService.addCountries(countries);
        return ResponseEntity.status(201)
                .body(Response.builder()
                        .message("The required countries are added successfully").build());
    }

    @GetMapping
    public List<Country> getCountries() {
       return countryService.getCountries();
    }

    @GetMapping("{countryId}")
    public void getCountry(@PathVariable("countryId") Integer countryId,
                                    HttpServletResponse response) {
         countryService.getCountry(countryId, response);
    }

    @DeleteMapping("{countryId}")
    public void deleteCountry(@PathVariable("countryId") Integer countryId,
                           HttpServletResponse response) {
        countryService.deleteCountry(response, countryId);
    }

    @PatchMapping("update")
    public void updateCountry(@RequestParam("countryId") Integer countryId,
                              @RequestParam("name") String name,
                              HttpServletResponse response) {
        countryService.updateCountry(countryId, name, response);
    }

}

@Builder
@Getter
class Response {
    private String message;
}
