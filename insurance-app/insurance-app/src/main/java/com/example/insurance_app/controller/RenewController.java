package com.example.insurance_app.controller;

import com.example.insurance_app.dto.ResponseMessageDto;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.*;
import com.example.insurance_app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RenewController {

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

    @Autowired
    private RenewService renewService;

    @PostMapping("/policy/renew/{pid}/{phid}/{eid}")
    public ResponseEntity<?> policyRenew(@PathVariable int pid,
                                         @PathVariable int phid,
                                         @PathVariable int eid,
                                         Renew renew,
                                         ResponseMessageDto dto) {

        // need to validate policyHolderId and fetch PolicyHolder obj
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
        PolicyPolicyHolder policyPolicyHolder = null;
        try {
            policyPolicyHolder = policyPolicyHolderService.verify(policy, policyHolder);
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

		/*update policy_policyholder table with new
		 	date_of_purchase, -- current date
		 	date_of_renewal,  --plus 1 year
		 	premium  -- calculate based on claims
			This info should be inserted in renewal_info table for record keeping.
		 */
        policyPolicyHolder.setDateOfPuchase(LocalDate.now());
        policyPolicyHolder.setDateOfRenewal(LocalDate.now().plusYears(1).plusDays(8));
        double premium = 0;
        try {
            claimService.isLodged(policy.getId(), policyHolder.getId(), policyPolicyHolder.getDateOfPuchase(), policyPolicyHolder.getDateOfRenewal());
            premium = policyPolicyHolder.getPremium() + (policyPolicyHolder.getPremium() * 0.20);
        } catch (ResourceNotFoundException e) {
            premium = policyPolicyHolder.getPremium() - (policyPolicyHolder.getPremium() * 0.10);
        }

        renew.setPhp(policyPolicyHolder);
        renew.setPreviousPremium(policyPolicyHolder.getPremium());
        renew.setCurrentPremium(premium);
        renew.setCurrentDateOfPurchase(LocalDate.now());
        renew.setPreviousDateOfPurchase(policyPolicyHolder.getDateOfPuchase());
        renew.setExecutive(executive);

        renew = renewService.insert(renew);

        policyPolicyHolder.setDateOfPuchase(LocalDate.now().plusYears(1).plusDays(8));
        policyPolicyHolder.setPremium(premium);

        policyPolicyHolderService.insert(policyPolicyHolder);

        return ResponseEntity.ok(renew);
    }
}