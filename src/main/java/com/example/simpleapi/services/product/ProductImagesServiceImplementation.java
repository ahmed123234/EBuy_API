package com.example.simpleapi.services.product;

import com.example.simpleapi.Repositories.ProductImagesRepository;
import com.example.simpleapi.entities.product.DescriptiveImages;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImagesServiceImplementation implements ProductImagesService{

    private final ProductImagesRepository imagesRepository;
    @Override
    public void addImage(DescriptiveImages image, HttpServletResponse response) {

        imagesRepository.save(image);
        Map<String, String> body = new HashMap<>();

        body.put("message", "Product Images are saved successfully");
        Response.setResponse(response,201, body );
    }

    @Override
    public void displayImage(Integer descriptiveImagesId, HttpServletResponse response) {

        Optional<DescriptiveImages> descriptiveImages = imagesRepository.findById(descriptiveImagesId);

        // check if country is present
        Response.checkObjectAvailability(response, descriptiveImages,
                "No such descriptive Images with the specified id %s", descriptiveImagesId);

    }

    @Override
    public List<DescriptiveImages> displayAllImage() {
        return imagesRepository.findAll();
    }

}
