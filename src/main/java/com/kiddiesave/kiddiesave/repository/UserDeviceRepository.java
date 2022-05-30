package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice,Integer> {
}
