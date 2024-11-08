package com.example.insurance_app.repository;

import com.example.insurance_app.enums.Policy_Category;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {
    @Query("select ph from PolicyPolicyHolder pph join pph.policyHolder ph where pph.premium >= ?1")
    List<PolicyHolder> getPolicyHoldersByPremiumAmount(double amt);

    @Query("select p from PolicyPolicyHolder pph "
            + " join pph.policyHolder ph "
            + " join pph.policy p where ph.id=?1")
    List<Policy> getPolicyDetails(int phid);
    @Query("select ph from PolicyPolicyHolder pph "
            + " join pph.policyHolder ph "
            + " join pph.policy p where p.policyCategory=?1")
    List<PolicyHolder> getPolicyHoldersByPolicyCategory(Policy_Category policyCategory);

}
