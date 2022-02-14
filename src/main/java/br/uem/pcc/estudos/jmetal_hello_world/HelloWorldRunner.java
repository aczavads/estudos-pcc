package br.uem.pcc.estudos.jmetal_hello_world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.sequencesolution.impl.CharSequenceSolution;
import org.uma.jmetal.util.AbstractAlgorithmRunner;

public class HelloWorldRunner extends AbstractAlgorithmRunner {
	private static String possibleGeneValues = "!,.:;?áÁãÃâÂõÕôÔóÓéêíÉÊQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ";

	public static void main(String[] args) {
		Problem<CharSequenceSolution> problem;
		Algorithm<CharSequenceSolution> algorithm;
		MutationOperator<CharSequenceSolution> mutation = new MutationOperator<CharSequenceSolution>() {
			@Override
			public CharSequenceSolution execute(CharSequenceSolution source) {
				Random r = new Random();
				if (r.nextDouble() <= getMutationProbability()) {
					char mutationGene = possibleGeneValues.charAt(r.nextInt(possibleGeneValues.length()));
					int positionToMutate = r.nextInt("Hello world!".length());
					source.variables().remove(positionToMutate);
					source.variables().add(positionToMutate, mutationGene);
				}
				return source;
			}

			@Override
			public double getMutationProbability() {
				return 0.3;
			}
		};
		CrossoverOperator<CharSequenceSolution> crossover = new CrossoverOperator<CharSequenceSolution>() {
			@Override
			public List<CharSequenceSolution> execute(List<CharSequenceSolution> source) {
					//System.out.println(">>>> " + source.size());
					CharSequenceSolution[] children = crossover(source.get(0), source.get(1));
					source = new ArrayList<>();
					source.add(children[0]);
					source.add(children[1]);
//					System.out.println(children[0]);
//					System.out.println(children[1]);
				return source;
			}

			@Override
			public double getCrossoverProbability() {
				return 1;
			}

			@Override
			public int getNumberOfRequiredParents() {
				return 2;
			}

			@Override
			public int getNumberOfGeneratedChildren() {
				return 2;
			}

			public CharSequenceSolution[] crossover(CharSequenceSolution individuo1, CharSequenceSolution individuo2) {
				Random r = new Random();

				// sorteia o ponto de corte
				int pontoCorte1 = r.nextInt((individuo1.variables().size() / 2) - 2) + 1;
				int pontoCorte2 = r.nextInt((individuo1.variables().size() / 2) - 2)
						+ individuo1.variables().size() / 2;
			
				CharSequenceSolution[] filhos = new CharSequenceSolution[2];

				// pega os genes dos pais
				String genePai1 = individuo1.variables().stream().map(String::valueOf).collect(Collectors.joining());
				String genePai2 = individuo2.variables().stream().map(String::valueOf).collect(Collectors.joining());

				String geneFilho1;
				String geneFilho2;

				// realiza o corte,
				geneFilho1 = genePai1.substring(0, pontoCorte1);
				geneFilho1 += genePai2.substring(pontoCorte1, pontoCorte2);
				geneFilho1 += genePai1.substring(pontoCorte2, genePai1.length());

				geneFilho2 = genePai2.substring(0, pontoCorte1);
				geneFilho2 += genePai1.substring(pontoCorte1, pontoCorte2);
				geneFilho2 += genePai2.substring(pontoCorte2, genePai2.length());

				// cria o novo indivíduo com os genes dos pais
				filhos[0] = individuo1.copy();
				filhos[1] = individuo2.copy();
				
				filhos[0].variables().clear();
				for (Character c : geneFilho1.toCharArray()) {
					filhos[0].variables().add(c);
				}
				filhos[1].variables().clear();
				for (Character c : geneFilho2.toCharArray()) {
					filhos[1].variables().add(c);
				}

				return filhos;
			}

		};
		SelectionOperator<List<CharSequenceSolution>, CharSequenceSolution> selection = new SelectionOperator<List<CharSequenceSolution>, CharSequenceSolution>() {
			@Override
			public CharSequenceSolution execute(List<CharSequenceSolution> source) {
				//System.out.println("CharSequenceSolution.execute(List<CharSequenceSolution> source). source.size() =  " + source.size());
		        Random r = new Random();
		        
		        List<CharSequenceSolution> possibleParents = new ArrayList<>();
		        possibleParents.add(source.get(r.nextInt(source.size())));
		        possibleParents.add(source.get(r.nextInt(source.size())));
		        possibleParents.add(source.get(r.nextInt(source.size())));
		        possibleParents.sort((a,b) -> Double.compare(b.objectives()[0],a.objectives()[0]));
		        
				return possibleParents.get(0);
			}
		};

		problem = new HelloWorldProblem("Hello world!".length());

		algorithm = new GeneticAlgorithmBuilder<>(problem, crossover, mutation)
				.setPopulationSize(50)
				.setMaxEvaluations(1000000) // um a mais que a população ele dispara o método execute de SelectionOperator... Why????
				.setSelectionOperator(selection)
				.setVariant(GeneticAlgorithmBuilder.GeneticAlgorithmVariant.GENERATIONAL)
				.build();
		algorithm.run();
		System.out.println("---");
		System.out.println(algorithm.getResult());
		System.out.println("Foi. :D");
	}

}
