package com.test.services;

import com.test.models.*;
import com.test.repo.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;


@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;

    @Autowired
    public HouseServiceImpl(
            HouseRepository repository) {
        this.repository = repository;
    }


    private void validateNewHouse(House newHouse) {}

    @Override
    public void saveNewHouse(House newHouse, Account account) throws IOException {
        validateNewHouse(newHouse);
        newHouse.setAccount(account);
        repository.save(newHouse);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<House> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void updateHouse(House newHouse, Account account) throws IOException {
        newHouse.setAccount(account);
        repository.save(newHouse);
    }
}
