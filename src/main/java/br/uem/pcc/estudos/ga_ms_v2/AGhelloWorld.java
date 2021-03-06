package br.uem.pcc.estudos.ga_ms_v2;

public class AGhelloWorld {

    public static void main(String[] args) {

    	String[] monolito = {
			"[EMPRÉSTIMO] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO] ManterEmpréstimo.avaliar()",
			"[FINANCEIRO] GerenciarMulta.calcular()",
			"[FINANCEIRO] GerenciarMulta.receber()",
			"[FINANCEIRO] GerenciarMulta.estornar()",
			"[FINANCEIRO] GerenciarMulta.calcularJuros()",
			"[FINANCEIRO] GerenciarMulta.baixar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.avaliar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO2] ManterEmpréstimo.avaliar()",
			"[FINANCEIRO2] GerenciarMulta.calcular()",
			"[FINANCEIRO2] GerenciarMulta.receber()",
			"[FINANCEIRO2] GerenciarMulta.calcular()",
			"[FINANCEIRO2] GerenciarMulta.receber()",
			"[FINANCEIRO2] GerenciarMulta.estornar()",
			"[FINANCEIRO2] GerenciarMulta.calcularJuros()",
			"[FINANCEIRO2] GerenciarMulta.baixar()",
			"[EMPRÉSTIMO9] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO9] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO9] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO9] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO9] ManterEmpréstimo.avaliar()",
			"[FINANCEIRO9] GerenciarMulta.calcular()",
			"[FINANCEIRO9] GerenciarMulta.receber()",
			"[FINANCEIRO9] GerenciarMulta.estornar()",
			"[FINANCEIRO9] GerenciarMulta.calcularJuros()",
			"[FINANCEIRO9] GerenciarMulta.baixar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.avaliar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.emprestar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.devolver()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.cancelar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.estonar()",
			"[EMPRÉSTIMO29] ManterEmpréstimo.avaliar()",
			"[FINANCEIRO29] GerenciarMulta.calcular()",
			"[FINANCEIRO29] GerenciarMulta.receber()",
			"[FINANCEIRO29] GerenciarMulta.calcular()",
			"[FINANCEIRO29] GerenciarMulta.receber()",
			"[FINANCEIRO29] GerenciarMulta.estornar()",
			"[FINANCEIRO29] GerenciarMulta.calcularJuros()",
			"[FINANCEIRO29] GerenciarMulta.baixar()"
		};        			

    	
        //Define a solução
        //Algoritimo.setSolucao(Individuo.comGenes("1111222221"));
        //Define os caracteres existentes
    	Algoritimo.setMonolito(monolito);
        Algoritimo.setCaracteres("1234");
        //taxa de crossover de 60%
        Algoritimo.setTaxaDeCrossover(0.6);
        //taxa de mutação de 3%
        Algoritimo.setTaxaDeMutacao(0.3);
        //elitismo
        boolean eltismo = true;
        //tamanho da população
        int tamPop = 1000;
        //numero máximo de gerações
        int numMaxGeracoes = 100;

        //define o número de genes do indivíduo baseado na solução
        int numGenes = monolito.length;

        //cria a primeira população aleatérioa
        Populacao populacao = new Populacao(numGenes, tamPop);
        
        boolean temSolucao = false;
        int geracao = 0;
        
        //loop até o critério de parada
        while (!temSolucao && geracao < numMaxGeracoes) {
            geracao++;

            //cria nova populacao
            populacao = Algoritimo.novaGeracao(populacao, eltismo);

            System.out.println("Geração " + geracao + " | Aptidão: " + populacao.getIndivduo(0).getAptidao() + " | Melhor: " + populacao.getIndivduo(0).getGenes());
            
            //verifica se tem a solucao
            temSolucao = populacao.temSolocao(Algoritimo.getSolucao());
        }

        if (geracao == numMaxGeracoes) {
            System.out.println("Número Maximo de Gerações | " + populacao.getIndivduo(0).getGenes() + " " + populacao.getIndivduo(0).getAptidao());
        }

        if (temSolucao) {
            System.out.println("Encontrado resultado na geração " + geracao + " | " + populacao.getIndivduo(0).getGenes() + " (Aptidão: " + populacao.getIndivduo(0).getAptidao() + ")");
        }
    }
}