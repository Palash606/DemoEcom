package com.example.insurance_app.service;

import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Executive;
import com.example.insurance_app.repository.ExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutiveService {

    @Autowired
    private ExecutiveRepository executiveRepository;

    public Executive insert(Executive executive) {

        return executiveRepository.save(executive);
    }

    public Executive validate(int eid) throws ResourceNotFoundException {
        Optional<Executive> optional = executiveRepository.findById(eid);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("Executive ID Invalid");


        return optional.get();
    }

}
