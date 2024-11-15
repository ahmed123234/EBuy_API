package com.example.simpleapi;

import com.example.simpleapi.Repositories.ReviewRepository;
import com.example.simpleapi.Repositories.UserRepository;
import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.entities.country.Country;
import com.example.simpleapi.entities.department.Department;
import com.example.simpleapi.security.PasswordEncoder;
import com.example.simpleapi.services.country.CountryServiceImplementation;
import com.example.simpleapi.services.department.DepartmentServiceImplementation;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

import static com.example.simpleapi.entities.User.UserRole.ADMIN_ROLE;
import static com.example.simpleapi.entities.User.UserRole.USER_ROLE;

@SpringBootApplication
public class SimpleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner (UserRepository userRepository, PasswordEncoder passwordEncoder) {
       return args ->

               userRepository.saveAll(
       List.of(
               User.builder()
                       .firstname("Ahmad")
                       .lastname("Ghannam")
                       .username("ahmad_Gh")
                       .password(passwordEncoder.bCryptPasswordEncoder().encode("1234"))
                       .email("ahmad@gmail.com")
                       .role(ADMIN_ROLE)
                       .mobile("7838848484")
                       .JoinDate(new Date())
                       .build(),
               User.builder()
                       .firstname("Ali")
                       .lastname("Qadan")
                       .username("ali_Gh")
                       .password(passwordEncoder.bCryptPasswordEncoder().encode("1234"))
                       .email("ali@gmail.com")
                       .role(USER_ROLE)
                       .mobile("7838848484")
                       .JoinDate(new Date())
                       .build(),
               User.builder()
                       .firstname("Akram")
                       .lastname("Fareq")
                       .username("akram_Gh")
                       .password(passwordEncoder.bCryptPasswordEncoder().encode("1234"))
                       .email("akram@gmail.com")
                       .role(USER_ROLE)
                       .mobile("7838848484")
                       .JoinDate(new Date())
                       .build()
//               new User("amjad_Gh",
//                       passwordEncoder.bCryptPasswordEncoder().encode("1234"),
//                       "amjad@gmail.com"
//               ),
//               new User(
//                       "asad_Gh",
//                       passwordEncoder.bCryptPasswordEncoder().encode("1234"),
//                       "asad@gmail.com"
//               )
               )
       );
    }

//    @Bean
//    ApplicationRunner applicationRunner (UserServiceImplementation userServiceImplementation) {
//        return args -> userServiceImplementation.register(
//                User.builder()
//
//                        .username("ahmad_Gh1")
//                        .password("1234")
//                        .email("ahmad1@gmail.com")
//                        .build()
//        );
//    }

    @Bean
    ApplicationRunner countryRunner (CountryServiceImplementation countryServiceImplementation) {
        return args -> countryServiceImplementation.addCountry(new Country(
                "Jordan"
        ));
    }

    @Bean
    CommandLineRunner DepartmentRunner (DepartmentServiceImplementation departmentServiceImplementation) {
        return args -> {

            departmentServiceImplementation.addDepartment(
                    new Department("Shoes", "shoes@yaho.com")
            );

            departmentServiceImplementation.addDepartment(
                    new Department("Clothes", "clothes@yaho.com")
            );
        };
    }


    @Bean
    CommandLineRunner ReviewRunner (ReviewRepository reviewRepository) {
        return args -> {
//            reviewRepository.save(Review.builder().description("khjhj")
//                    .rating(4.5).build());
        };
    }


//    @Bean
//    CommandLineRunner OfferRunner (OfferRepository offerRepository) {
//        return args -> {
//
//           offerRepository.save(
//                new Offer(null,)
//            );
//
//
//        };
//    }
}
