package com.devventura.bankingcrud.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public ResponseEntity<List<PeopleDTO>> findAll(){	
		List<PeopleDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}	
	
	@PostMapping
	public ResponseEntity<PeopleDTO> insert(@RequestBody @Valid PeopleDTO dto, BindingResult result){		
		String newcpf = dto.getCpf().replace(".", "").replace("-", "");
		dto.setCpf(newcpf);
		
		if(result.hasErrors()) {			
			HttpHeaders headers = new HttpHeaders();
			for (ObjectError objError : result.getAllErrors()) {
				headers.add("Erro", objError.getDefaultMessage());
			}		
			return ResponseEntity.status(400).headers(headers).body(dto);
		}
		
		if(!service.checkIsCpfSaved(dto) || !service.checkIsCpfSaved(dto))	
			return ResponseEntity.status(400).header("Erro", "Email ou CPF já cadastrados").body(dto);

		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);		
	}
}
