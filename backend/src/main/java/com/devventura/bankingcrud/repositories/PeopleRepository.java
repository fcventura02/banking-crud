package com.devventura.bankingcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devventura.bankingcrud.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	People findOneByEmail(String email);
	People findOneByCpf(String cpf);
}
