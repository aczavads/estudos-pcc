package br.uem.pcc.estudos.manterCor;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;



@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cor {
	@Id
	@Setter(AccessLevel.NONE)
	@EqualsAndHashCode.Include
	private String id;
	private String nome;
	
	public Cor() {
		id = UUID.randomUUID().toString();
	}
	
	

}


