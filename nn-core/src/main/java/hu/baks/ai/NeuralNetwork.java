package hu.baks.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class NeuralNetwork {

	private List<Neuron.Input> inputLayer;
	private List<List<Neuron>> hiddenLayers;
	private int[] hiddenLayerSizes;
	
	public NeuralNetwork(int[] layerSizes, Function<Double, Double> transferFunction) {
		inputLayer = new ArrayList<>(layerSizes[0]);
		for (int i = 0; i < layerSizes[0]; i++) {
			Neuron.Input inputNeuron = new Neuron.Input();
			inputLayer.add(inputNeuron);
		}
		hiddenLayers = new ArrayList<>();
		for (int layerIndex = 0; layerIndex < hiddenLayerSizes.length; layerIndex++) {
			List<Neuron> layer = new ArrayList<>(hiddenLayerSizes[layerIndex]);
			List<? extends Neuron> prevLayer = layerIndex == 0 ? inputLayer : hiddenLayers.get(layerIndex-1);
			for (int neuronIndex = 0; neuronIndex < hiddenLayerSizes[layerIndex]; neuronIndex++) {
				Neuron neuron = new Neuron(transferFunction, prevLayer);
				layer.add(neuron);
			}
			hiddenLayers.add(layer);
		}
	}
	
	public double[] run(double[] input) {
		for (int inIndex = 0; inIndex < inputLayer.size(); inIndex++) {
			inputLayer.get(inIndex).setInputValue(input[inIndex]);
		}
		
		List<Neuron> outputLayer = hiddenLayers.get(hiddenLayers.size() - 1);
		
		double[] result = new double[outputLayer.size()];
		for (int outIndex = 0; outIndex < outputLayer.size(); outIndex++) {
			result[outIndex] = outputLayer.get(outIndex).transfer();
		}
		
		return result;
	}
}
