package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateEmailRequest;
import com.kiddiesave.kiddiesave.entity.EmailValidationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailValidationDataRepository extends JpaRepository<EmailValidationData,Long> {
    EmailValidationData findEmailValidationDataByEmailAndRequestId(String email,String requestId);
}
