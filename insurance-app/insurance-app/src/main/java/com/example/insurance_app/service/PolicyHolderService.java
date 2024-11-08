package com.example.insurance_app.service;

import com.example.insurance_app.enums.Policy_Category;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import com.example.insurance_app.repository.PolicyHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHolderService {

    @Autowired
    private PolicyHolderRepository policyHolderRepository;

    public PolicyHolder insert(PolicyHolder policyholder) {

        return policyHolderRepository.save(policyholder);
    }

    public List<PolicyHolder> getAll() {

        return policyHolderRepository.findAll();
    }

    public PolicyHolder validate(int id) throws ResourceNotFoundException {

        Optional<PolicyHolder> optional = policyHolderRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("policyholder id invalid");

        PolicyHolder policyholder = optional.get();
        return policyholder;

    }

    public void delete(int id) {
        policyHolderRepository.deleteById(id);

    }

    public List<PolicyHolder> insertInBatch(List<PolicyHolder> list) {
        return policyHolderRepository.saveAll(list);
    }

    public List<PolicyHolder> getPolicyHolderByPremiumAmount(double amt) {
        return policyHolderRepository.getPolicyHoldersByPremiumAmount(amt);
    }

    public List<Policy> getPolicyDetails(int phid) {
        return policyHolderRepository.getPolicyDetails(phid);
    }

    public List<PolicyHolder> getPolicyHoldersByPolicyCategory(Policy_Category category) {
        return policyHolderRepository.getPolicyHoldersByPolicyCategory(category);
    }
}