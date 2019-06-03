package com.dfs.fullstackhateoas_backend.resources;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.dfs.fullstackhateoas_backend.assembler.CapabilityResourceAssembler;
import com.dfs.fullstackhateoas_backend.domain.Capability;
import com.dfs.fullstackhateoas_backend.services.CapabilityService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class CapabilityController {

	private CapabilityService capabilityService;
	
	private CapabilityResourceAssembler assembler;
	
	
	public CapabilityController(CapabilityService capabilityService, CapabilityResourceAssembler assembler) {
		this.capabilityService = capabilityService;
		this.assembler = assembler;
	}

	@GetMapping
	public Resources<Resource<Capability>> getAllCapabilities(){
		return new Resources<>(capabilityService.getAllCapabilities().stream()
				.map(capability -> assembler.toResource(capability)).collect(Collectors.toList()),
				new Link("http://localhost:8080/dashboard").withRel("createCapability")
				);
	}
	
	@GetMapping("/{id}")
	public Resource<?> getCapability(@PathVariable Long id){
		return assembler.toResource(capabilityService.findCapById(id));
	}
	
	@PostMapping
	public Object createCapability(@RequestBody Capability capability,BindingResult result) {
		
		if(result.hasErrors()) return capabilityService.errorMap(result);
		
		return assembler.toResource(capabilityService.saveCapability(capability));
		
	}
	
	@PutMapping("/{id}")
	public Object updateCapability(@PathVariable Long id,@Valid @RequestBody Capability capability, BindingResult result) {
		
		if(result.hasErrors()) return capabilityService.errorMap(result);
		
		
		return assembler.toResource(capabilityService.updateCapability(id,capability));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCapability(@PathVariable Long id) {
		
		capabilityService.deleteCapability(id);
		
		return new ResponseEntity<String>("Capability Deleted",HttpStatus.OK);
		
	}
}
