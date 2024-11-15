package com.example.simpleapi.Repositories.product;

import com.example.simpleapi.entities.Review;
import com.example.simpleapi.entities.department.Department;
import com.example.simpleapi.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query("update Product set  productName = ?1 , category = ?2, description = ?3," +
            " price = ?4, quantity = ?5, image = ?6, department = ?7," +
            " ranking = ?8, unitsInOrder = ?9, unitsInStock = ?10")

    void insertProduct(String productName, String category, String description,
                       Double price, Long quantity, String image,
                       Department department,
//                       Integer offerId,
                       Integer ranking, Long unitsInOrder, Long unitsInStock);

    @Query("select department  from Product where productId = ?1")
    Department getProductDepartment(Integer productId);

    @Query("select p.productName, AVG (r.rating) from Product p inner join  Review r " +
            "on p.productId = r.product.productId " +
            "where p.productId = ?1 group by p.productId")
    Review getProductRating(Integer productId);
}
