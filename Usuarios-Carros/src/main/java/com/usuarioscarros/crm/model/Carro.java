package com.usuarioscarros.crm.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Carro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -232436207897783662L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , name = "ano")
	@NotNull(message = "Invalid fields")
	private int year;
	
	@Column(nullable = false,unique=true)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")	
	private String licensePlate;
	
	@Column(nullable = false)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String model;
	
	@Column(nullable = false)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String color;

}
