package br.uem.pcc.estudos.ga_ms_v2;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppMétodosPorFuncionalidade {
	
	public static void main(String[] args) {
    	String[] monolito = {
			"[EMPRÉSTIMO] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.estonar()",
			"[FINANCEIRO] GerenciarMulta.calcular()",
			"[FINANCEIRO] GerenciarMulta.receber()",
			"[FINANCEIRO] GerenciarMulta.estornar()",
			"[FINANCEIRO] GerenciarMulta.calcularJuros()",
			"[FINANCEIRO] GerenciarMulta.baixar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.avaliar()"
		};        			

    	Map<String, Long> funcionalidades =  Stream.of(monolito) 
    			.map(v -> v.substring(v.indexOf('[')+1, v.lastIndexOf(']')))
    			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    	
    	funcionalidades.forEach((k, v) -> System.out.println(k + " ==> " + v));
		
		
	}

}
