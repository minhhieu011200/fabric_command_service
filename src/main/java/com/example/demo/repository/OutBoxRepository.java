package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Outbox;

@Repository
public interface OutBoxRepository extends JpaRepository<Outbox,Long>{
}
