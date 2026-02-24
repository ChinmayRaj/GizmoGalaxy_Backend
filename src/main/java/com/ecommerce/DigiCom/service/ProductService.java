package com.ecommerce.DigiCom.service;

import com.ecommerce.DigiCom.model.Product;
import com.ecommerce.DigiCom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        System.out.println("name is entered");
        product.setImageType(image.getContentType());
        System.out.println("content is entered");
        product.setImageData(image.getBytes());
        System.out.println("image is entered");
        System.out.println(product);

       return productRepo.save(product);


       // return productRepo.save(product);
    }

    public int updateProduct( Product product, MultipartFile imageFile)throws IOException {


        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        productRepo.save(product);
int id=productRepo.findById(product.getId()).get().getId();
       return id;



    }

    public void deleteProduct(int id) {

    productRepo.deleteById(id);

    }

    public List<Product> searchProduct(String searchwords) throws Exception{

        List<Product> products=new ArrayList<>();
        products=productRepo.searchProduct(searchwords);

        return products;
    }
}
