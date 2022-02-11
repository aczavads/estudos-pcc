package br.uem.pcc.estudos.jmetal_hello_world;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.binarysolution.BinarySolution;
import org.uma.jmetal.solution.sequencesolution.impl.CharSequenceSolution;
import org.uma.jmetal.util.AbstractAlgorithmRunner;

public class HelloWorldRunner extends AbstractAlgorithmRunner {

	public static void main(String[] args) {
		Problem<CharSequenceSolution> problem;
		Algorithm<CharSequenceSolution> algorithm;
		MutationOperator<CharSequenceSolution> mutation = new MutationOperator<CharSequenceSolution>() {
			@Override
			public CharSequenceSolution execute(CharSequenceSolution source) {
				return source;
			}

			@Override
			public double getMutationProbability() {
				return 0;
			}
		};
		CrossoverOperator<CharSequenceSolution> crossover = new CrossoverOperator<CharSequenceSolution>() {
			@Override
			public List<CharSequenceSolution> execute(List<CharSequenceSolution> source) {
				return source;
			}
			@Override
			public double getCrossoverProbability() {
				return 0;
			}
			@Override
			public int getNumberOfRequiredParents() {
				return 2;
			}

			@Override
			public int getNumberOfGeneratedChildren() {
				return 2;
			}
		};
		SelectionOperator<List<CharSequenceSolution>, CharSequenceSolution> selection = new SelectionOperator<List<CharSequenceSolution>, CharSequenceSolution>() {
			@Override
			public CharSequenceSolution execute(List<CharSequenceSolution> source) {
				System.out.println("CharSequenceSolution.execute(List<CharSequenceSolution> source)");
				return source.get(0);
			}
		};

		problem = new HelloWorldProblem("Hello world!".length());

		algorithm = new GeneticAlgorithmBuilder<>(problem, crossover, mutation)
				.setPopulationSize(20)
				.setMaxEvaluations(21) //um a mais que a população ele dispara o método execute de SelectionOperator... Why????
				.setSelectionOperator(selection)
				.setVariant(GeneticAlgorithmBuilder.GeneticAlgorithmVariant.GENERATIONAL)
				.build();
		algorithm.run();
		System.out.println("---");
		System.out.println(algorithm.getResult());
		System.out.println("Foi. :D");
	}

}
