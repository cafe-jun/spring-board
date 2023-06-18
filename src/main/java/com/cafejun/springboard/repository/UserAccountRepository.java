package com.cafejun.springboard.repository;

import com.cafejun.springboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
}
