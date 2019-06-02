package com.dfs.fullstackhateoas_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dfs.fullstackhateoas_backend.domain.Capability;
import com.dfs.fullstackhateoas_backend.exceptions.CapabilityException;
import com.dfs.fullstackhateoas_backend.repository.CapabilityRepository;

@Service
public class CapabilityService {

	private CapabilityRepository capabilityRepository;
	
	public CapabilityService(CapabilityRepository capabilityRepository) {
		this.capabilityRepository = capabilityRepository;
	}
	public List<Capability> getAllCapabilities(){
		return capabilityRepository.findAll();
	}
	public Capability findCapById(Long id) {
		return capabilityRepository.findById(id).
				orElseThrow(() -> new CapabilityException("Capability with ID: "+id+"Not found"));
	}

}
