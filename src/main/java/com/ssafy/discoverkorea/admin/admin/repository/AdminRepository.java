package com.ssafy.discoverkorea.admin.admin.repository;

import com.ssafy.discoverkorea.admin.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("select a.id from Admin a where a.loginId=:loginId")
    Optional<Long> existLoginId(@Param("loginId") String loginId);

    @Query("select a.id from Admin a where a.tel=:tel")
    Optional<Long> existTel(@Param("tel") String tel);

    @Query("select a.id from Admin a where a.email=:email")
    Optional<Long> existEmail(@Param("email") String email);

    Optional<Admin> findByLoginId(@Param("loginId") String loginId);
}
