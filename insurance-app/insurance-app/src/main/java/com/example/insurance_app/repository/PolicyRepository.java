package com.example.insurance_app.repository;

import com.example.insurance_app.enums.PolicyType;
import com.example.insurance_app.enums.Policy_Category;
import com.example.insurance_app.model.Policy;
import com.example.insurance_app.model.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

    @Query(value = "select * from policy where policy_category = ?1", nativeQuery = true)
    List<Policy> getPoliciesByCategoryNativeSql(String policyCategory);

    @Query("select p from Policy p where p.policyCategory=?1")
    List<Policy> getPoliciesByCategoryNativeJPQL(Policy_Category policyCategory);

    List<Policy> getPoliciesByPolicyCategory(Policy_Category policyCategory);

    @Query(value = "select * from policy where policy_type = ?1", nativeQuery = true)
    List<Policy> getPoliciesByPolicyTypeNativeSQL(String policyType);

    @Query("select p from Policy p where p.policyType=?1")
    List<Policy> getPoliciesByPolicyTypeNativeJPQL(PolicyType policyType);

    List<Policy> getPoliciesByPolicyType(PolicyType policyType);

    @Query("select p from PolicyPolicyHolder pph join pph.policy p where pph.premium >= ?1")
    List<Policy> getPolicyByPremiumAmount(double amt);


}