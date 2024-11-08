package com.example.insurance_app.controller;

import com.example.insurance_app.dto.PolicyResponseDto;
import com.example.insurance_app.dto.PolicyStatDto;
import com.example.insurance_app.dto.ResponseMessageDto;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import com.example.insurance_app.model.PolicyPolicyHolder;
import com.example.insurance_app.service.PolicyHolderService;
import com.example.insurance_app.service.PolicyPolicyHolderService;
import com.example.insurance_app.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PolicyPolicyHolderController {

    @Autowired
    private PolicyHolderService policyHolderService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyPolicyHolderService policyPolicyHolderService;
    /*
     * Policy holder is going to purchase the policy via this api
     * path: /policyholder/policy/purchase
     * path-param: policyHolderId, policyId
     * method: POST
     * body:
     * return: PolicyPolicyHolder
     * */

    @PostMapping("/policyholder/policy/purchase/{policyHolderId}/{policyId}")
    public ResponseEntity<?> purchasePolicy(@PathVariable int policyHolderId,
                                            @PathVariable int policyId,
                                            @RequestBody PolicyPolicyHolder policyPolicyHolder,
                                            ResponseMessageDto dto) {

        //I am getting the values of dateOfRenewal and premium from request body.

        //i need to validate policyHolderId and fetch PolicyHolder obj
        PolicyHolder policyHolder = null;
        try {
            policyHolder = policyHolderService.validate(policyHolderId);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
        Policy policy = null;
        //i need to validate policyId and fetch Policy obj
        try {
            policy = policyService.validate(policyId);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        //attach the above 2 objects to policyHolderPolicy object.
        policyPolicyHolder.setPolicyHolder(policyHolder);
        policyPolicyHolder.setPolicy(policy);

        //set dateOfPurchase to current date in policyHolderPolicy object
        policyPolicyHolder.setDateOfPuchase(LocalDate.now());

        //save this obj in the DB and return it
        policyPolicyHolder = policyPolicyHolderService.insert(policyPolicyHolder);
        return ResponseEntity.ok(policyPolicyHolder);

    }

    /*
     * This api will activate or de-activate the policy
     * If and when the premium is paid, the policy will get activated.
     * */
    @PostMapping("/policy/state/{phpid}")
    public ResponseEntity<?> changePolicyStatus(@RequestParam boolean status,
                                                @PathVariable int phpid,
                                                ResponseMessageDto dto) {
        //validate phpid and fetch object
        PolicyPolicyHolder policyPolicyHolder = null;

        try {
            policyPolicyHolder = policyPolicyHolderService.validate(phpid);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        policyPolicyHolder.setActive(status);

        //save this obj in the DB and return it
        policyPolicyHolder = policyPolicyHolderService.insert(policyPolicyHolder);
        return ResponseEntity.ok(policyPolicyHolder);
    }

    @GetMapping("/policy-holder/policy")
    public List<PolicyResponseDto> getAllPolicyHolderWithPolicyDetails() {
        List<PolicyResponseDto> list = policyPolicyHolderService.getAllPolicyHolderWithPolicyDetails();
        return list;
    }

    @GetMapping("/policy/policyholder/stat")
    public List<PolicyStatDto> getPolicyStats() {
        List<PolicyStatDto> list = policyPolicyHolderService.getPolicyStats();
        return list;
    }
}