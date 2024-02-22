package com.example.web_shop.controllers;

import com.example.web_shop.models.Product;
import com.example.web_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

//    @PostMapping("/reserve/{id}")
//    public void reserveProduct(@PathVariable Long id, @RequestParam int availableQuantity) {
//        productService.reserveProduct(id, availableQuantity);
//    }
//    @PostMapping("/reserve")
//    public void reserveProduct(@RequestParam Long id, @RequestParam int availableQuantity) {
//        productService.reserveProduct(id, availableQuantity);
//    }
    @PostMapping("/reserve/{id}")
    public void reserveProduct(@PathVariable Long id, @RequestParam int availableQuantity) {
        productService.reserveProduct(id, availableQuantity);
    }

}



//@Data
//@RestController
//public class ProductController
//{
//    @Autowired
//    private RestTemplate restTemplate;
//
//    /**
//     * Метод получения продукта по его id из микросервиса product-service и в нём конкретного класса Product
//     **/
//    @GetMapping("/products/{id}")
//    public String getProductById(@PathVariable Long id)
//    {
//        Product product = restTemplate.getForObject("http://product-service/products/" + id, Product.class);
//        return product.toString();
//    }
//}
        // 1) restTemplate это: экземпляр класса RestTemplate,
        // предоставляемый Spring Framework для отправки HTTP-запросов к удаленным серверам и обработки ответов.

        // 2) .getForObject: Это метод RestTemplate, который отправляет GET-запрос по указанному URL
        // и ожидает получить ответ в виде объекта, который будет автоматически преобразован в объект Java,
        // используя указанный класс (в данном случае Product.class).

        // 3) "http://product-service/products/" + id:
        // Это URL-адрес, по которому будет отправлен запрос.
        // В данном случае мы отправляем запрос к микросервису с именем product-service
        // (предполагается, что этот микросервис доступен по данному URL)
        // и запрашиваем информацию о продукте с указанным id.

        // 4) Product.class: Это класс, в который мы ожидаем преобразовать ответ от сервера.
        // Метод getForObject автоматически преобразует ответ в объект этого класса,
        // если формат ответа соответствует ожидаемому.








