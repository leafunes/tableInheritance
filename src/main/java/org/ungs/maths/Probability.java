package org.ungs.maths;

import java.util.Collection;

public class Probability {
	
	public static Double computeStandardDeviation(Collection<Double> collection) {
        if (collection.size() == 0) {
            return Double.NaN;
        }

        final double average =
                collection.stream()
                      .mapToDouble((x) -> x.doubleValue())
                      .summaryStatistics()
                      .getAverage();

        final double rawSum = 
        			collection.stream()
                      .mapToDouble((x) -> Math.pow(x.doubleValue() - average,
                                                   2.0))
                      .sum();

        return Math.sqrt(rawSum / (collection.size() - 1));
    }

}
