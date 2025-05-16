package com.jyo.practiceprojectformList.Repository;

import com.jyo.practiceprojectformList.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
//    @Query("SELECT o FROM OTP o WHERE LOWER(o.email) = LOWER(:email)")
//    Optional<OTP> findByEmail(@Param("email") String email);

        Optional<OTP> findByEmail(String email);//findByEmail(String email);
    void deleteByEmail(String email
    );

//    void setExpiryTime(OTP otpentity);
}
