package br.uem.pcc.estudos.manterCor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cores")
public class CorController {
	@Autowired
	private CorService service;

	@GetMapping
	public List<Cor> get() {
		return service.recuperarTodas();
	}

	@GetMapping("/maiorId")
	public Cor getMaiorId() {
		return service.recuperarComMaiorId();
	}

	@PostMapping
	public String post(@RequestBody Cor nova) {
		service.salvar(nova);
		return nova.getId();
	}

}
