package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.controller.ProductController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductTest {

    @InjectMocks
    ProductController productController;
    @Mock
    ProductDao productDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getProduct(){
        Product p= new Product();
        p.setId(2);
        when(productDao.findById(2)).thenReturn(p);
        MappingJacksonValue product=productController.afficherUnProduit(2);
        int idProduct= ((Product)product.getValue()).getId();
        verify(productDao).findById(2);
        Assert.assertEquals(2,idProduct);
    }

    @Test
    public void getProducts() {
        List<Product> ListProducts = new ArrayList<>();

        when(productDao.findAll()).thenReturn(ListProducts);

        MappingJacksonValue products = productController.listeProduits();

        verify(productDao).findAll();
        for (Product pr :ListProducts) {
           Assert.assertEquals(pr, products);

        }

    }
    @Test
    public void margeProduits() {
        List<Product> listProds = new ArrayList<>();
        when(productDao.findAll()).thenReturn(listProds);
        List<String> res = productController.calculerMargeProduit();
        verify(productDao).findAll();
        for (Product prod : listProds) {
            Assert.assertEquals(prod, res);
        }
    }

    @Test
    public void ProduitPlusCher() {
        List<Product> ListProdCher = new ArrayList<>();
        when(
                productDao.chercherUnProduitCher(400))
                .thenReturn((List<Product>) ListProdCher);

        List<Product> Prod = productController.testeDeRequetes(400);

        verify(productDao).chercherUnProduitCher(400);
        for (Product pr : ListProdCher) {
           Assert.assertEquals(pr, Prod);

        }
    }

    @Test
    public void createProduct() {
        Product product = new Product(6, "Galaxy", 1000, 160);
        productController.ajouterProduit(product);

        verify(productDao, times(1)).save(product);
    }


    @Test
    public void deleteProduct() {
        productController.supprimerProduit(1);
        verify(productDao, times(1)).delete(1);

    }



    }
