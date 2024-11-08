package com.example.insurance_app.controller;

import com.example.insurance_app.dto.ResponseMessageDto;
import com.example.insurance_app.enums.Policy_Category;
import com.example.insurance_app.exception.ResourceNotFoundException;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import com.example.insurance_app.service.PolicyHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PolicyHolderController {

    @Autowired
    private PolicyHolderService policyHolderService;

    @PostMapping("/holder/add")
    public PolicyHolder addPolicyHolder(@RequestBody PolicyHolder policyHolder) {
        return policyHolderService.insert(policyHolder);
    }

    @GetMapping("/holder/fetch")
    public List<PolicyHolder> getAllHolders() {
        List<PolicyHolder> list = policyHolderService.getAll();
        System.out.println(list);
        return list;
    }


    @DeleteMapping("/holder/delete/{id}")
    public ResponseEntity<?> deletePolicyHolder(@PathVariable int id, ResponseMessageDto dto) {
        try {
            policyHolderService.validate(id);
            policyHolderService.delete(id);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        dto.setMsg("Holder Deleted");
        return ResponseEntity.ok(dto);


    }

    @PutMapping("/policyHolder/update/{id}")
    public ResponseEntity<?> updatePolicyHolder(@PathVariable int id, @RequestBody PolicyHolder newPolicyHolder, ResponseMessageDto dto) {
        try {
            PolicyHolder existingPolicyHolderDb = policyHolderService.validate(id);
            if (newPolicyHolder.getName() != null)
                existingPolicyHolderDb.setName(newPolicyHolder.getName());
            if (newPolicyHolder.getContact() != null)
                existingPolicyHolderDb.setContact(newPolicyHolder.getContact());
            if (newPolicyHolder.getPanNumber() != null)
                existingPolicyHolderDb.setPanNumber(newPolicyHolder.getPanNumber());
            if (newPolicyHolder.getAge() != 0)
                existingPolicyHolderDb.setAge(newPolicyHolder.getAge());
            //re save this existing policy having new updated value
            existingPolicyHolderDb = policyHolderService.insert(existingPolicyHolderDb);
            return ResponseEntity.ok(existingPolicyHolderDb);

        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    //display policy holders that have paid premium more than the premium amount
    @GetMapping("/policyHolder/premium/get")
    public ResponseEntity<?> getPolicyByPremiumAmount(@RequestParam String amount) {
        try {
            double amt = Double.parseDouble(amount);
            List<PolicyHolder> list = policyHolderService.getPolicyHolderByPremiumAmount(amt);
            return ResponseEntity.ok(list);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/policy/details/{phid}")
    public ResponseEntity<?> getPolicyDetails(@PathVariable int phid, ResponseMessageDto dto) {
        try {
            policyHolderService.validate(phid);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        List<Policy> list = policyHolderService.getPolicyDetails(phid);
        return ResponseEntity.ok(list);
    }

    // give policy holder details based on policy category
    @GetMapping("/policyHolder/category/get")
    public ResponseEntity<?> getPolicyHolderDetails(@RequestParam String category) {
        try {
            Policy_Category policyCategory = Policy_Category.valueOf(category);
            List<PolicyHolder> list = policyHolderService.getPolicyHoldersByPolicyCategory(policyCategory);
            return ResponseEntity.ok(list);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
