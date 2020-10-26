package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.model.User;
import com.ecommerce.microcommerce.web.exceptions.ProduitGratuitException;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Api( description="API pour es opérations CRUD sur les produits.")

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;


    //Récupérer la liste des produits
    @ApiOperation(value = "Produits", response = Product.class,tags="GetProducts")
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)

    public MappingJacksonValue listeProduits() {

        Iterable<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

//    @RequestMapping("/user")
//        @GetMapping
//        public ResponseEntity<User> getUser(){
//
//
//
//            return  new ResponseEntity<User>(user, HttpStatus.OK);
//        }


    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Produits/{id}")

    public  MappingJacksonValue afficherUnProduit(@PathVariable int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product produit = productDao.findById(id);

        if(produit==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE. Écran Bleu si je pouvais.");

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produit);


        if (user.getRole().equals("user")) {
            FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", SimpleBeanPropertyFilter.serializeAllExcept("prixAchat","prix"));
        produitsFiltres.setFilters(listDeNosFiltres);
        return produitsFiltres;
        }
        else{
            FilterProvider listDeNosFiltresAdmin = new SimpleFilterProvider().addFilter("monFiltreDynamique", SimpleBeanPropertyFilter.serializeAllExcept("prixAchat"));
            produitsFiltres.setFilters(listDeNosFiltresAdmin);
            return produitsFiltres;
        }


    }

    @ApiOperation(value = "Calcule la marge d'un produit", response = Product.class)
    @GetMapping(value = "/AdminProduits",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> calculerMargeProduit(){
        List<Product> produits = productDao.findAll();
        List<String> s =new ArrayList<>();
        double marge ;
        for (Product prod : produits) {
            marge = prod.getPrix()-prod.getPrixAchat();
            s.add(prod +":"+ marge);
        }
        return s;
    }

    @ApiOperation(value = "Tri les produits par ordre alphabetiques ", response = Product.class,tags="GetProducts")
    @GetMapping(value = "/Tri")
    public List<Product> trierProduitsParOrdreAlphabetique(){
        return productDao.findAll(new Sort(Sort.Direction.ASC, "nom"));
    }


    @ApiOperation(value = "Permet l'ajout d'un produits ", response = Product.class,tags="AddProduct")
    @PostMapping(value = "/Produits")

    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();
        if (productAdded.getPrix() == 0)
           throw new ProduitGratuitException("Produit egale 0");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @ApiOperation(value = "Supprimer un Produit", response = Product.class,tags="DeleteProduct")
    @DeleteMapping (value = "/Produits/{id}")
    public void supprimerProduit(@PathVariable int id) {

        productDao.delete(id);
    }

    @ApiOperation(value = "Modification d'un Produit", response = Product.class,tags="EditProduct")
    @PutMapping (value = "/Produits")
    public void updateProduit(@RequestBody Product product) {

        productDao.save(product);
    }


    //Pour les tests
    @GetMapping(value = "test/produits/{prix}")
    public List<Product>  testeDeRequetes(@PathVariable int prix) {

        return productDao.chercherUnProduitCher(400);
    }



}
