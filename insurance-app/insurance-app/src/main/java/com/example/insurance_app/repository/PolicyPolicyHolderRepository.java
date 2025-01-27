package com.example.insurance_app.repository;

import com.example.insurance_app.model.PolicyPolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PolicyPolicyHolderRepository extends JpaRepository<PolicyPolicyHolder, Integer> {

    @Query(value = "select * from policy_policyholder where policy_id=?1 AND policy_holder_id=?2 AND is_active=?3", nativeQuery = true)
    Optional<PolicyPolicyHolder> verify(int pid, int phid, boolean activeStatus);

    @Query("select ph.id as policy_holder_id, ph.name as policy_holder_name, " +
            "ph.panNumber as pan, pph.dateOfPurchase as date_of_purchase, pph.dateOfRenewal as date_of_renewal," +
            "pph.premium as premium_paid, p.policyCategory as policy_category, p.policyType as policy_type " +
            "from PolicyPolicyHolder pph " +
            "JOIN pph.policy p " +
            "JOIN pph.policyHolder ph")
    List<Object[]> getAllPolicyHolderWithPolicyDetails();

    @Query("select p.policyCategory,count(ph.id)  "
            + " from PolicyPolicyHolder pph "
            + " JOIN pph.policy p "
            + " JOIN pph.policyHolder ph "
            + " group by p.policyCategory")
    List<Object[]> getPolicyStats();
}