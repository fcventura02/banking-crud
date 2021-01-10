package com.devventura.bankingcrud.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devventura.bankingcrud.dto.PeopleDTO;
import com.devventura.bankingcrud.entities.People;
import com.devventura.bankingcrud.repositories.PeopleRepository;

import ch.qos.logback.classic.Logger;

@Service
public class PeopleService {
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Transactional(readOnly =  true)
	public List<PeopleDTO> findAll(){
		List<People> list = peopleRepository.findAll();
		return list.stream().map(x -> new PeopleDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public PeopleDTO insert(PeopleDTO dto){
		People people = new People(null, dto.getName(), dto.getEmail(), dto.getCpf(), dto.getBirth());
		people = peopleRepository.save(people);
		return new PeopleDTO(people);
	}
	
	@Transactional(readOnly =  true)
	public Boolean checkIsCpfSaved(PeopleDTO dto){
		People peopleIsSaved  = peopleRepository.findOneByCpf(dto.getCpf());
		return peopleIsSaved == null;
	}
	
	@Transactional(readOnly =  true)
	public Boolean checkIsEmailSaved(PeopleDTO dto){
		People peopleIsSaved  = peopleRepository.findOneByEmail(dto.getEmail());
		return peopleIsSaved == null;
	}
}
