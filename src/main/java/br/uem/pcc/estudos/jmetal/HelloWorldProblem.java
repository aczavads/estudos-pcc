package br.uem.pcc.estudos.jmetal;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.uma.jmetal.problem.integerproblem.IntegerProblem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;

public class HelloWorldProblem implements IntegerProblem {

	@Override
	public Integer getLowerBound(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUpperBound(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<Integer, Integer>> getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfVariables() {		
		return "Hello world!".getBytes().length;
	}

	@Override
	public int getNumberOfObjectives() {
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
	public IntegerSolution evaluate(IntegerSolution solution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntegerSolution createSolution() {
		// TODO Auto-generated method stub
		return null;
	}

}
