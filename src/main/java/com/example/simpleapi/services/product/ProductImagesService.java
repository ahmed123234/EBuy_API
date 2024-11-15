package com.example.simpleapi.services.product;

import com.example.simpleapi.entities.product.DescriptiveImages;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductImagesService {
    void addImage(DescriptiveImages image,  HttpServletResponse response);

    void displayImage(Integer descriptiveImagesId, HttpServletResponse response);

    List<DescriptiveImages> displayAllImage();

}
