package hu.baks.ai;

import java.util.function.Function;

public class Sigmoid implements Function<Double, Double> {

	@Override
	public Double apply(Double x) {
		return x / Math.sqrt(1 + x*x);
	}

}
