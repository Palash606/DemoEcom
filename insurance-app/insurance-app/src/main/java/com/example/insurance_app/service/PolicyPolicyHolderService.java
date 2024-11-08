package com.example.insurance_app.service;

import com.example.insurance_app.dto.PolicyResponseDto;
import com.example.insurance_app.dto.PolicyStatDto;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import com.example.insurance_app.model.PolicyPolicyHolder;
import com.example.insurance_app.repository.PolicyPolicyHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyPolicyHolderService {

    @Autowired
    private PolicyPolicyHolderRepository policyPolicyHolderRepository;

    public PolicyPolicyHolder insert(PolicyPolicyHolder policyPolicyHolder) {

        return policyPolicyHolderRepository.save(policyPolicyHolder);
    }

    public PolicyPolicyHolder validate(int phpid) throws ResourceNotFoundException {
        Optional<PolicyPolicyHolder> optional
                = policyPolicyHolderRepository.findById(phpid);

        if (optional.isEmpty())
            throw new ResourceNotFoundException("ID given is Invalid, pls provide main contract ID");

        return optional.get();
    }

    public PolicyPolicyHolder verify(Policy policy, PolicyHolder policyHolder) throws ResourceNotFoundException {
        Optional<PolicyPolicyHolder> optional = policyPolicyHolderRepository.verify(policy.getId(), policyHolder.getId(), true);

        if (optional.isEmpty())
            throw new ResourceNotFoundException("Policy does not exist, claim cannot be registered..");

        return optional.get();
    }

    public List<PolicyResponseDto> getAllPolicyHolderWithPolicyDetails() {
        List<Object[]> listObjArray = policyPolicyHolderRepository.getAllPolicyHolderWithPolicyDetails();
        List<PolicyResponseDto> list = new ArrayList<>();
        for (Object[] obj : listObjArray) {
            int policy_holder_id = (int) obj[0];
            String policy_holder_name = (String) obj[1];
            String pan = (String) obj[2];
            LocalDate date_of_purchase = (LocalDate) obj[3];
            LocalDate date_of_renewal = (LocalDate) obj[4];
            double premium_paid = (double) obj[5];
            String policy_category = obj[6].toString();
            String policy_type = obj[7].toString();

            PolicyResponseDto dto = new PolicyResponseDto();
            dto.setPolicyHolderId(policy_holder_id);
            dto.setPolicyHolderName(policy_holder_name);
            dto.setPolicyHolderPan(pan);
            dto.setDateOfPurchase(date_of_purchase);
            dto.setDateOfRenewal(date_of_renewal);
            dto.setPremium_paid(premium_paid);
            dto.setPolicyType(policy_type);
            dto.setPolicyCategory(policy_category);
            list.add(dto);
        }

        return list;
    }

    public List<PolicyStatDto> getPolicyStats() {
        List<Object[]> list = policyPolicyHolderRepository.getPolicyStats();
        List<PolicyStatDto> statDtoList = new ArrayList<>();
        for (Object[] obj : list) {
            PolicyStatDto dto = new PolicyStatDto();
            dto.setLabel((obj[0].toString()));
            Long x = (Long) obj[1];
            dto.setData(String.valueOf(x));
            statDtoList.add(dto);
        }
        return statDtoList;
    }

}