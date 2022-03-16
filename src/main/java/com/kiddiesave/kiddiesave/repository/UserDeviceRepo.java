package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepo extends JpaRepository<UserDevice,Integer> {
}
