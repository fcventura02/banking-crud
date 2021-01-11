package com.devventura.bankingcrud.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.devventura.bankingcrud.entities.People;

public class PeopleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Email(message="Email inválido")
	private String email;
	@NotNull
	@CPF(message="CPF inválido")
	private String cpf;
	@NotNull
	private LocalDate birth;
	
	public PeopleDTO() {}

	public PeopleDTO(Long id, String name, String email, String cpf, LocalDate birth) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birth = birth;
	}
	
	public PeopleDTO(People entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		cpf = entity.getCpf();
		birth = entity.getBirth();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeopleDTO other = (PeopleDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
