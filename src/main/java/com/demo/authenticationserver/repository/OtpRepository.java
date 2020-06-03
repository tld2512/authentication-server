package com.demo.authenticationserver.repository;

import com.demo.authenticationserver.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUsername(String username);

    @Query("select code from Otp where username=:username")
    Optional<String> findCodeByUsername(@Param("username") String username);
}
