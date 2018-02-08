package hu.baks.ai;

import java.util.List;
import java.util.function.Function;

public class Neuron {
	
	private List<? extends Neuron> inputs;
	private double fixWeight;
	private double[] weights;
	
	private Function<Double, Double> transferFunction;
	
	protected Neuron() {
	}
	
	public Neuron(Function<Double, Double> transferFunction, List<? extends Neuron> inputs) {
		this.transferFunction = transferFunction;
		this.inputs = inputs;
		this.weights = new double[inputs.size()];
		for (int index = 0; index < weights.length; index++) {
			weights[index] = Math.random();
		}
	}
	
	public double transfer() {
		double sum = fixWeight;
		for (int index = 0; index < inputs.size(); index++) {
			Neuron n = inputs.get(index);
			sum += weights[index] * n.transfer();
		}
		return transferFunction.apply(sum);
	}
	
	public double getFixWeight() {
		return fixWeight;
	}
	
	public double[] getWeights() {
		return weights;
	}
	
	public void upgrade(double fixWeight, double[] weights) {
		this.fixWeight = fixWeight;
		this.weights = weights;
	}
	
	public static class Input extends Neuron {
		private double inputValue;
		
		public Input() {
		}
		
		@Override
		public double transfer() {
			return inputValue;
		}
		
		public void setInputValue(double inputValue) {
			this.inputValue = inputValue;
		}
	}
}
