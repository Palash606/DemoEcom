package com.example.insurance_app.service;

import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Claim;
import com.example.insurance_app.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public Claim insert(Claim claim) {
        return claimRepository.save(claim);
    }

    public Claim isLodged(int policyId, int policyHolderId, LocalDate dateOfPurchase, LocalDate dateOfRenewal) throws ResourceNotFoundException {
        Optional<Claim> optional = claimRepository.isLodged(policyId, policyHolderId, dateOfPurchase, dateOfRenewal);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("No Claim");
        }
        return optional.get();
    }
}