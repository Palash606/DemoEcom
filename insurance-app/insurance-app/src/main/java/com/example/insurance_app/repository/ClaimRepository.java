package com.example.insurance_app.repository;

import com.example.insurance_app.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
    @Query(value = "select * from claim where policy_id=?1 AND policy_holder_id=?2 AND claim_date BETWEEN ?3 AND ?4", nativeQuery = true)
    Optional<Claim> isLodged(int policyId, int policyHolderId, LocalDate dateOfPuchase, LocalDate renewalDate);
}