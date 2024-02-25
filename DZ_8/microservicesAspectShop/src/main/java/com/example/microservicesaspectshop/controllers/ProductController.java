package com.example.microservicesaspectshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Data
@RestController
public class ProductController
{
    @Autowired
    private RestTemplate restTemplate;

    /** Метод получения продукта по его id из микросервиса product-service и в нём конкретного класса Product **/
    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable Long id)
    {
        Product product = restTemplate.getForObject("http://product-service/products/" + id, Product.class);
        return product.toString();
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
    }

}





//import com.example.microservicesaspectshop.configs.SpringConfig;
//import com.example.microservicesaspectshop.models.Product;
//import com.example.microservicesaspectshop.services.ProductService;
//import lombok.Data;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.client.RestTemplate;
//
//@Data
//@Controller
//public class ProductController
//{
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @GetMapping("/product/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        String productServiceUrl = "http://product-service/products/" + id;
//        ResponseEntity<Product> response = restTemplate.getForEntity(productServiceUrl, Product.class);
//        return response;
//    }
//
//}
