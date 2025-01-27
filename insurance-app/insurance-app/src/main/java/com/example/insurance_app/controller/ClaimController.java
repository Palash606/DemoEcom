package com.example.insurance_app.controller;

import com.example.insurance_app.dto.ResponseMessageDto;
import com.example.insurance_app.enums.ClaimStatus;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Claim;
import com.example.insurance_app.model.Executive;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import com.example.insurance_app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ClaimController {

    @Autowired
    private PolicyHolderService policyHolderService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private ExecutiveService executiveService;

    @Autowired
    private ClaimService claimService;

    @Autowired
    private PolicyPolicyHolderService policyPolicyHolderService;

    @PostMapping("/claim/process/{pid}/{phid}/{eid}")
    public ResponseEntity<?> processClaim(@PathVariable int pid,
                                          @PathVariable int phid,
                                          @PathVariable int eid,
                                          @RequestBody Claim claim,
                                          ResponseMessageDto dto) {

        //i need to validate policyHolderId and fetch PolicyHolder obj
        PolicyHolder policyHolder = null;
        try {
            policyHolder = policyHolderService.validate(phid);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
        Policy policy = null;
        //i need to validate policyId and fetch Policy obj
        try {
            policy = policyService.validate(pid);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        //check if the policy is active
        try {
            policyPolicyHolderService.verify(policy, policyHolder);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        //i need to validate executiveId and fetch Executive obj
        Executive executive = null;
        try {
            executive = executiveService.validate(eid);
        } catch (ResourceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        claim.setPolicy(policy);
        claim.setPolicyHolder(policyHolder);
        claim.setExecutive(executive);
        claim.setClaimDate(LocalDate.now());
        claim.setClaimStatus(ClaimStatus.IN_PROCESS);

        claim = claimService.insert(claim);
        return ResponseEntity.ok(claim);
    }
}