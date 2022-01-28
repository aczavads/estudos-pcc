package br.uem.pcc.estudos.ga_ms;

public class AGhelloWorld {

    public static void main(String[] args) {

        //Define a solução
        Algoritimo.setSolucao(new Individuo(new String[][] {
        	new String[] {
            		"[EMPRÉSTIMO] ManterEmpréstimo.emprestar()",
            		"[EMPRÉSTIMO] ManterEmpréstimo.devolver()",
            		"[EMPRÉSTIMO] ManterEmpréstimo.cancelar()",
            		"[EMPRÉSTIMO] ManterEmpréstimo.estonar()",
            		"[EMPRÉSTIMO] ManterEmpréstimo.avaliar()"
        	},
        	new String[] {
            		"[FINANCEIRO] GerenciarMulta.calcular()",
            		"[FINANCEIRO] GerenciarMulta.receber()",
            		"[FINANCEIRO] GerenciarMulta.estornar()",
            		"[FINANCEIRO] GerenciarMulta.calcularJuros()",
            		"[FINANCEIRO] GerenciarMulta.baixar()"        			
        	} 
        }));
        //Define os caracteres existentes
        Algoritimo.setMétodos(new String[] {
        		"[EMPRÉSTIMO] ManterEmpréstimo.emprestar()",
        		"[EMPRÉSTIMO] ManterEmpréstimo.devolver()",
        		"[EMPRÉSTIMO] ManterEmpréstimo.cancelar()",
        		"[EMPRÉSTIMO] ManterEmpréstimo.estonar()",
        		"[EMPRÉSTIMO] ManterEmpréstimo.avaliar()",
        		"[FINANCEIRO] GerenciarMulta.calcular()",
        		"[FINANCEIRO] GerenciarMulta.receber()",
        		"[FINANCEIRO] GerenciarMulta.estornar()",
        		"[FINANCEIRO] GerenciarMulta.calcularJuros()",
        		"[FINANCEIRO] GerenciarMulta.baixar()"
        });
        
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
        int numGenes = Algoritimo.getSolucao().length();

        //cria a primeira população aleatérioa
        Populacao populacao = new Populacao(numGenes, tamPop);

        boolean temSolucao = false;
        int geracao = 0;

        System.out.println("Iniciando... Aptidão da solução: "+Algoritimo.getSolucao().length());
        
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