package com.dfs.fullstackhateoas_backend.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.dfs.fullstackhateoas_backend.domain.Capability;
import com.dfs.fullstackhateoas_backend.resources.CapabilityController;
@Component
public class CapabilityResourceAssembler implements ResourceAssembler<Capability,Resource<Capability>>{
	
	@Override
	public Resource<Capability> toResource(Capability entity){
		return new Resource<>(entity,
				linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("getThisCapability"),
				linkTo(methodOn(CapabilityController.class).getCapability(entity.getId())).withRel("deleteThisCapability"),
				linkTo(methodOn(CapabilityController.class).getAllCapabilities()).withRel("getAllCapabilities"),
				new Link("http://localhost:8080/dashboard"+entity.getId()).withRel("updateThisCapability")
				
				);
	}

}
