package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {


}
