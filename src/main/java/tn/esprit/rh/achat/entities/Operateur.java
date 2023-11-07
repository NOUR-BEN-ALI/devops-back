package tn.esprit.rh.achat.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operateur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull

	private Long idOperateur;
	@NonNull

	private String nom;
	@NonNull

	private String prenom;
	@NonNull


	private String password;
	@OneToMany
	@JsonIgnore
	private Set<Facture> factures =new HashSet<>();
	
}
