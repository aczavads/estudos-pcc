package br.uem.pcc.estudos.ga_ms_v2;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Individuo {

    private String genes = "";
    private int aptidao = 0;

    public Individuo() {
	}
    
    //gera um indivíduo aleatório
    public Individuo(int numGenes) {
        genes = "";
        Random r = new Random();
        
        String caracteres = Algoritimo.getCaracteres();
        
        for (int i = 0; i < numGenes; i++) {
            genes += caracteres.charAt(r.nextInt(caracteres.length()));
        }
        
        geraAptidao();        
    }

    //cria um indivíduo com os genes definidos
    public Individuo(String genes) {    
        this.genes = genes;
        
        Random r = new Random();
        //se for mutar, cria um gene aleatório
        if (r.nextDouble() <= Algoritimo.getTaxaDeMutacao()) {
            String caracteres = Algoritimo.getCaracteres();
            String geneNovo="";
            int posAleatoria = r.nextInt(genes.length());
            for (int i = 0; i < genes.length(); i++) {
                if (i==posAleatoria){
                    geneNovo += caracteres.charAt(r.nextInt(caracteres.length()));
                }else{
                    geneNovo += genes.charAt(i);
                }
                
            }
            this.genes = geneNovo;   
        }
        geraAptidao();
    }

    //gera o valor de aptidão, será calculada pelo número de bits do gene iguais ao da solução
    private void geraAptidao() {
    	int linhas = Algoritimo.getFuncionalidades().size();
		int colunas = Algoritimo.getCaracteres().length();
		int[][] distribuiçãoFuncionalidadesPorMS = new int[linhas][colunas];
    	
    	System.out.println("=========");
    	for (int i = 0; i < this.genes.length(); i++) {
    		int númeroDoMicroserviço = Integer.parseInt(String.valueOf(this.genes.charAt(i)));
    		int númeroDaFuncionalidade = Algoritimo.getFuncionalidades().indexOf(Optional.of(Algoritimo.getMonolito()[i]).map(v -> v.substring(v.indexOf('[')+1, v.lastIndexOf(']'))).get());
    		//System.out.println(Optional.of(Algoritimo.getMonolito()[i]).map(v -> v.substring(v.indexOf('[')+1, v.lastIndexOf(']'))).get());
    		//int númeroDaFuncionalidade= 0;    			
    		//System.out.println(this.genes.charAt(i) + " ==> " + Algoritimo.getMonolito()[i] + "  ==> " + númeroDoMicroserviço + ":" + númeroDaFuncionalidade);
    		distribuiçãoFuncionalidadesPorMS[númeroDaFuncionalidade][númeroDoMicroserviço-1]++;
    	}
    	calcularAptidãoPelaDistribuiçãoDeFuncionalidades(linhas, colunas, distribuiçãoFuncionalidadesPorMS);
    	descontarAptidãoPorMicroserviçosSemFuncionalidades(linhas, colunas, distribuiçãoFuncionalidadesPorMS);
    	    	
    	System.out.println("Aptidão: " + aptidao);
    }

	private void descontarAptidãoPorMicroserviçosSemFuncionalidades(int linhas, int colunas,
			int[][] distribuiçãoFuncionalidadesPorMS) {
		int quantidadeMsSemFuncionalidades = 0;
		for (int coluna = 0; coluna < colunas; coluna++) {
			int quantidadeFuncionalidades = 0;
			for (int linha = 0; linha < linhas; linha++) {
				quantidadeFuncionalidades += distribuiçãoFuncionalidadesPorMS[linha][coluna];
			}
			if (quantidadeFuncionalidades == 0) {
				quantidadeMsSemFuncionalidades++;
			}
		}
		int médiaDeMétodosEsperadaPorMS = Algoritimo.getMonolito().length / Algoritimo.getCaracteres().length();
		aptidao -= médiaDeMétodosEsperadaPorMS * quantidadeMsSemFuncionalidades;
	}

	private void calcularAptidãoPelaDistribuiçãoDeFuncionalidades(int linhas, int colunas,
			int[][] distribuiçãoFuncionalidadesPorMS) {
		aptidao = 0;
    	System.out.println(this.genes);
    	for (int i = 0; i < linhas; i++) {
    		System.out.print("Funcionalidade: " +  i + "  ");
        	int maiorDistribuiçãoPorFuncionalidade = 0;
    		for (int j = 0; j < colunas; j++) {
    			if (distribuiçãoFuncionalidadesPorMS[i][j] > maiorDistribuiçãoPorFuncionalidade) {
    				maiorDistribuiçãoPorFuncionalidade = distribuiçãoFuncionalidadesPorMS[i][j]; 
    			}
    			System.out.print(distribuiçãoFuncionalidadesPorMS[i][j] + " "); 				
			}
    		aptidao += maiorDistribuiçãoPorFuncionalidade;
    		System.out.println();
		}
	}

    public int getAptidao() {    
        return aptidao;
    }

    public String getGenes() {
        return genes;
    }

	public static Individuo comGenes(String genes) {
		Individuo individuo = new Individuo();
		individuo.genes = genes;
		return individuo;
	}
}




