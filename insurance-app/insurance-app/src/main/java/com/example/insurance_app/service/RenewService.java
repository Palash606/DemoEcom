package com.example.insurance_app.service;

import com.example.insurance_app.model.Renew;
import com.example.insurance_app.repository.RenewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenewService {

    @Autowired
    private RenewRepository renewRepository;

    public Renew insert(Renew renew) {

        return renewRepository.save(renew);
    }
}
