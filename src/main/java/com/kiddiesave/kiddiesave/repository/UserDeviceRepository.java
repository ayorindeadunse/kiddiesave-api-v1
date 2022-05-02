package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Integer> {
}
