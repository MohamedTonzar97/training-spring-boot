package com.example.workshopmicroservices.springhystrixschoolservice.controller;

import com.example.workshopmicroservices.springhystrixschoolservice.delegate.ProductServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductServiceController {

    @Autowired
    ProductServiceDelegate productServiceDelegate;

    @GetMapping(value="/Produits/{id}")
    public  String getProductid(@PathVariable int id){
        return  productServiceDelegate.callProductService(id);
    }
    @GetMapping(value="/Produits")
    public  String getAllProduct(){
        return  productServiceDelegate.callAllProductService();
    }
    @GetMapping(value = "/Tri")
    public String getTrierProduitsOrdreAlphabetique(){
        return productServiceDelegate.callProductTriService();
    }

}
