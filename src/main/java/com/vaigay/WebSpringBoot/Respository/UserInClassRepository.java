package com.vaigay.WebSpringBoot.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaigay.WebSpringBoot.Entity.UserInClass;

public interface UserInClassRepository extends JpaRepository<UserInClass, Long> {

}
