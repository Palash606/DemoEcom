package com.example.insurance_app.service;

import com.example.insurance_app.enums.PolicyType;
import com.example.insurance_app.enums.Policy_Category;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy insert(Policy policy) {
        return policyRepository.save(policy);

    }

    public List<Policy> getAllPolicy() {

        return policyRepository.findAll();
    }

    public void delete(int id) {
        policyRepository.deleteById(id);

    }

    public Policy validate(int id) throws ResourceNotFoundException {
        Optional<Policy> optional = policyRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("policy id invalid");

        Policy policy = optional.get();
        return policy;

    }

    public List<Policy> insertInBatch(List<Policy> list) {
        return policyRepository.saveAll(list);

    }

    public List<Policy> getPolicyByCategory(Policy_Category policyCategory) {
        //return policyRepository.getPoliciesByCategoryNativeSql(policyCategory.toString());
        //return policyRepository.getPoliciesByCategoryNativeJPQL(policyCategory);

        return policyRepository.getPoliciesByPolicyCategory(policyCategory);
    }

    public List<Policy> getPolicyByType(PolicyType policyType) {
        // return policyRepository.getPoliciesByPolicyTypeNativeSQL(policyType.toString());
        //return policyRepository.getPoliciesByPolicyTypeNativeJPQL(policyType);
        return policyRepository.getPoliciesByPolicyType(policyType);
    }

    public List<Policy> getPolicyByPremiumAmount(double amt) {
        return policyRepository.getPolicyByPremiumAmount(amt);
    }

}