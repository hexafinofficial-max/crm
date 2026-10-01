package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long>{

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
