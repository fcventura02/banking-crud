package com.devventura.bankingcrud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devventura.bankingcrud.dto.PeopleDTO;
import com.devventura.bankingcrud.services.PeopleService;

@RestController
@RequestMapping(value = "/people")
public class PeopleController {
	@Autowired
	private PeopleService service;
	
	@PostMapping
	public ResponseEntity<PeopleDTO> insert(@RequestBody PeopleDTO dto){
		
		if(!service.checkIsCpfSaved(dto))	
			return ResponseEntity.status(400).body(dto);
		if(!service.checkIsCpfSaved(dto))	
			return ResponseEntity.status(400).body(dto);

		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);	
		
	}
}
