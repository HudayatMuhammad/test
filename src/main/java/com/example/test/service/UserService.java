package com.example.test.service;

import com.example.test.exception.UserRegistrationException;
import com.example.test.model.UserModel;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> login(String email, String password) {
        return userRepository.login(email, password);
    }

    public UserModel createUser(UserModel userModel) {
        Optional<UserModel> userOptional = userRepository.findByEmail(userModel.getEmail());
        if(userOptional.isPresent()) {
            throw new UserRegistrationException("User with email "+ userModel.getEmail()+" already exists");
        }

        return userRepository.save(userModel);
    }

    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }


    public Optional<UserModel> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
