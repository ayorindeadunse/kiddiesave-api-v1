package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberRequest;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends CrudRepository<ValidatePhoneNumberRequest,Long> {

}
