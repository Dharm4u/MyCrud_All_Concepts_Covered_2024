package com.example.demo.Controller;

import com.example.demo.Dto.PostDto;
import com.example.demo.Dto.PostResponse;
import com.example.demo.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //when we use restcontroller response body will be auto generated
@RequestMapping("api/posts")//to make universal URL or base URL
public class PostController {
    @Autowired
    private PostService postService;

    //create post rest api
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)// herer request body i.e postDto is coming from the user or we can say request object. Requestbody tells Spring to deserialize the JSON object in the request body into a PostDto object.This JSON object will be automatically converted into a Post object by Spring MVC and passed. Or it converts JSON file into POJO class
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED); //ResponseEntity is special class which you can use to modify status code ,the body header and footer of the response
        //ResponseEntity in Spring Boot represents the entire HTTP response, including the status code, headers, and body.
    }

    @GetMapping("/details/{id}")
    //this is path variable / URI template variable....Diffrence btw Pathvariable and Requestparam...
    public ResponseEntity<PostDto> getPostDetailsById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));

    }

    @PutMapping("/details/{id}")
    public ResponseEntity<PostDto> updatePostDetails(@PathVariable(value = "id") Long id, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePostById(id, postDto));


    }

    @GetMapping
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        return ResponseEntity.ok(postService.getPosts(pageNo, pageSize, sortBy, sortDir));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removePost(@PathVariable(value = "id") long id) {
        postService.deletePost(id);
    return ResponseEntity.ok("Post got deleted successfully with id :: "+id);

    }
}

//@Controller:
//
//@Controller is used to define a class as a controller in Spring MVC.
//It is typically used to create web applications where the response can be a web page (HTML), view, or model.
//Methods in a @Controller class return a logical view name or a ModelAndView object, which resolves to a view template (HTML page) to be rendered to the client.
//@RestController:
//
//@RestController is a specialization of @Controller and is used for RESTful web services.
//It combines @Controller and @ResponseBody, meaning that all methods in a @RestController class return data directly as the response body (usually in JSON or XML format), rather than relying on a view template.
//It is commonly used in RESTful APIs to build services that communicate using HTTP and exchange data in JSON or XML format.
//==
//Sure, let's simplify it with an example:
//
//Imagine you're building a web application where users can view and interact with a list of products.
//
//If you use @Controller, you're creating a regular web application. When a user visits a certain URL, say /products, the controller method associated with that URL might fetch data about products from a database, process it, and then return a view template (like an HTML page) that contains the product information nicely formatted for the user to see.
//java
//@Controller
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/products")
//    public String getProducts(Model model) {
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        return "products"; // This returns the name of the view template
//    }
//}
//In this case, the method returns the name of the view template ("products"), and Spring MVC knows to render the products.html template and send it back to the user's browser.
//
//Now, if you use @RestController, you're building an API. When a user sends an HTTP request to /api/products, the controller method associated with that URL might fetch the product data, process it, and then return the product information directly as JSON, without any HTML involved.
//java
//@RestController
//@RequestMapping("/api/products")
//public class ProductRestController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//}
//In this case, when a user sends an HTTP GET request to /api/products, Spring MVC serializes the list of products into JSON format and sends it back directly to the user's browser or client application. There's no HTML involved, just raw data in JSON format, which is perfect for building APIs that other systems can consume.

//=========

//Path variables and request parameters are both used to pass data from a client to a server in a RESTful API. However, they differ in how they are sent and accessed.
//
//Path Variable:
//Path variables are part of the URL path itself.
//They are specified within curly braces {} in the URL template.
//They are used to identify a specific resource or entity.
//Path variables are accessed using @PathVariable annotation in Spring MVC.
//Example: /api/users/{userId}
//java
//Copy code
//@GetMapping("/api/users/{userId}")
//public ResponseEntity<User> getUserById(@PathVariable Long userId) {
//    // Logic to fetch user by ID
//}
//Request Parameter:
//Request parameters are sent as key-value pairs in the URL query string or in the request body.
//They are optional and can be included in the URL after a ? character.
//Request parameters are used for filtering, sorting, or providing additional data to the server.
//Request parameters are accessed using @RequestParam annotation in Spring MVC.
//Example: /api/users?name=John&age=30
//java
//Copy code
//@GetMapping("/api/users")
//public ResponseEntity<List<User>> getUsersByNameAndAge(
//        @RequestParam String name,
//        @RequestParam int age) {
//    // Logic to fetch users by name and age
//}
//In summary, path variables are used to define and access variables within the URL path itself, while request parameters are used to send additional data to the server in the URL query string or request body.

