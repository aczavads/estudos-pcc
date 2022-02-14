package br.uem.pcc.estudos.jmetal_hello_world;

import java.util.Random;
import java.util.stream.Collectors;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.sequencesolution.impl.CharSequenceSolution;

public class HelloWorldProblem<T> implements Problem<CharSequenceSolution> {
	private int solutionLength;
	private String possibleGeneValues = "!,.:;?áÁãÃâÂõÕôÔóÓéêíÉÊQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ";

	public HelloWorldProblem(int solutionLength) {
		this.solutionLength = solutionLength;
	}

	@Override
	public int getNumberOfVariables() {
		return solutionLength;
	}

	@Override
	public int getNumberOfObjectives() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getNumberOfConstraints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharSequenceSolution evaluate(CharSequenceSolution solution) {
        String solucao = "Hello world!";
        int aptidao = 0;
        for (int i = 0; i < solucao.length(); i++) {
            if (solucao.charAt(i) == solution.variables().get(i)) {
                aptidao++;
            }
        }
		
		solution.objectives()[0] = aptidao;
		System.out.println(solution.variables() + " ==> Aptidão="+aptidao);
		return solution;
	}

	@Override
	public CharSequenceSolution createSolution() {
		CharSequenceSolution solution = new CharSequenceSolution(solutionLength, 1);
        Random r = new Random();
        
        for (int i = 0; i < solutionLength; i++) {
            solution.variables().add(i,	 possibleGeneValues.charAt(r.nextInt(possibleGeneValues.length())));
        }
		System.out.println("CharSequenceSolution.createSolution() " + solution.variables().stream().map(String::valueOf).collect(Collectors.joining()));		
		return solution;
	}

}
