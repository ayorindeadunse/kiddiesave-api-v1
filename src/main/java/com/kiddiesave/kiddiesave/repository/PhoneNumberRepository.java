package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.PhoneRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneRecord,Long> {
}
