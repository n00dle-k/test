package com.test.services;

import com.test.models.Account;
import com.test.models.House;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface HouseService {

    void saveNewHouse(House newHouse, Account account) throws IOException;

    void deleteById(long id);

    Optional<House> findById(long id);

    void updateHouse(House newHouse, Account account) throws IOException;

}
