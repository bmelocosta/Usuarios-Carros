package com.usuarioscarros.crm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
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
public class Usuario implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4481128241145472909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String firstName;
	
	@Column
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String lastName;
	
	@Column(unique=true)
	@Email(message = "Invalid fields")
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String email;
	
	@Column	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Invalid fields")
	private Date birthday;
	
	@Column(unique=true)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String login;
	
	@Column(nullable = false)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String password;
	
	@Column(nullable = false)
	@NotBlank(message = "Missing fields")
	@NotNull(message = "Invalid fields")
	private String phone;

	@Column
	@OneToMany
	@NotNull(message = "Invalid fields")
	private List<Carro> Cars;
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}	

}
