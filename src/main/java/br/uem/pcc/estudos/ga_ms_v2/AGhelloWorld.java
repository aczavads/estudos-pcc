package br.uem.pcc.estudos.ga_ms_v2;

public class AGhelloWorld {

    public static void main(String[] args) {

    	String[] métodos = {
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

    	
        //Define a solução
        Algoritimo.setSolucao(Individuo.comGenes("1111222221"));
        //Define os caracteres existentes
        Algoritimo.setCaracteres("12");
        //taxa de crossover de 60%
        Algoritimo.setTaxaDeCrossover(0.6);
        //taxa de mutação de 3%
        Algoritimo.setTaxaDeMutacao(0.3);
        //elitismo
        boolean eltismo = true;
        //tamanho da população
        int tamPop = 100;
        //numero máximo de gerações
        int numMaxGeracoes = 10000;

        //define o número de genes do indivíduo baseado na solução
        int numGenes = Algoritimo.getSolucao().getGenes().length();

        //cria a primeira população aleatérioa
        Populacao populacao = new Populacao(numGenes, tamPop);

        boolean temSolucao = false;
        int geracao = 0;

        System.out.println("Iniciando... Aptidão da solução: "+Algoritimo.getSolucao().getGenes().length());
        
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