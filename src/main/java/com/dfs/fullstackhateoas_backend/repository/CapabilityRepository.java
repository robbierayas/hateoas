package com.dfs.fullstackhateoas_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dfs.fullstackhateoas_backend.domain.Capability;

public interface CapabilityRepository extends JpaRepository<Capability,Long>{

}
