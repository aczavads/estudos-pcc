package br.uem.pcc.estudos.manterCor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorRepository extends JpaRepository<Cor, String> {

	@Query(value = "select id, nome from cor where id = select max(id) from cor", nativeQuery = true)
	Cor findCorComMaiorId();

	List<Cor> findByNomeContaining(String nome);

}
