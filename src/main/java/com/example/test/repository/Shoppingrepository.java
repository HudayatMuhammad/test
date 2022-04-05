package com.example.test.repository;

import com.example.test.model.Shopping;
import com.example.test.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Shoppingrepository extends JpaRepository<Shopping, Long> {

}
