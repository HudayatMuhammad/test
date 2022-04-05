package com.example.test.controller;

import com.example.test.model.Shopping;
import com.example.test.model.UserModel;
import com.example.test.service.ShoppingService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apii")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
    @GetMapping("/shopping")
    public List<Shopping> getAllUsers() {
        return shoppingService.findAllUsers();
    }
    @GetMapping("/shopping/{id}")
    public ResponseEntity<Shopping> getUserById(@PathVariable Long id) {
        return shoppingService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shopping createShopping(@RequestBody @Validated Shopping shopping) {
        return shoppingService.createShopping(shopping);
    }

    @PutMapping("/shopping/{id}")
    public ResponseEntity<Shopping> updateUser(@PathVariable Long id, @RequestBody @Validated Shopping shopping) {
        return shoppingService.findUserById(id)
                .map(userObj -> {
                    userObj.setId(id);
                    return ResponseEntity.ok(shoppingService.updateUser(userObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/shopping/{id}")
    public ResponseEntity<Shopping> deleteUser(@PathVariable Long id) {
        return shoppingService.findUserById(id)
                .map(user -> {
                    shoppingService.deleteUserById(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
