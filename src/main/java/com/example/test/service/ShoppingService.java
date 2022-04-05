package com.example.test.service;

import com.example.test.exception.UserRegistrationException;
import com.example.test.model.Shopping;
import com.example.test.model.UserModel;
import com.example.test.repository.Shoppingrepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingService {

    private Shoppingrepository shoppingrepository;

    @Autowired
    public ShoppingService(Shoppingrepository shoppingrepository) {
        this.shoppingrepository = shoppingrepository;
    }

    public Shopping createShopping(Shopping shopping) {
        Optional<Shopping> userOptional = shoppingrepository.findById(shopping.getId());
        if(userOptional.isPresent()) {
            throw new UserRegistrationException("User with email "+ shopping.getId()+" already exists");
        }

        return shoppingrepository.save(shopping);
    }

    public Shopping updateUser(Shopping shopping) {
        return shoppingrepository.save(shopping);
    }

    public List<Shopping> findAllUsers() {
        return shoppingrepository.findAll();
    }


    public Optional<Shopping> findUserById(Long id) {
        return shoppingrepository.findById(id);
    }

    public void deleteUserById(Long id) {
        shoppingrepository.deleteById(id);
    }
}
