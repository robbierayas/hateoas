package com.dfs.fullstackhateoas_backend.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dfs.fullstackhateoas_backend.domain.Capability;
import com.dfs.fullstackhateoas_backend.repository.CapabilityRepository;

@Configuration
public class InitialDataLoad {

	@Bean
	CommandLineRunner LoadDB (CapabilityRepository capabilityRepository) {
		return args -> {
            capabilityRepository.save(new Capability("Java", 100, 50));
            capabilityRepository.save(new Capability("ReactJS",70,20));
            capabilityRepository.save(new Capability("Python", 200, 100));
		};
	}
}
