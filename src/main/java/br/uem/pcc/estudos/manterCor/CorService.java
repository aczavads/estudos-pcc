package br.uem.pcc.estudos.manterCor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CorService {
	@Autowired
	private CorRepository repo;

	public void salvar(Cor c) {
		repo.save(c);
	}

	public void excluirPeloId(String id) {
		repo.deleteById(id);
	}

	public Cor recuperarComMaiorId() {
		return repo.findCorComMaiorId();
	}

	public List<Cor> recuperarTodas() {
		return repo.findAll();
	}

}
