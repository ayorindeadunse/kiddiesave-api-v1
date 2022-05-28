package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.PhoneRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneRecord,Long> {
}
