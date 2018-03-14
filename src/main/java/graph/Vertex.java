package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Vertex class for use with generic graphs
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class Vertex<T> {
    private final T value;
    // for weighted searches
    private double cost;
    // for A-star
    private double predictedCost;
    private Function<Vertex<T>, Double> distanceFunction;

    private List<Edge<T>> adjacent;

    public Vertex(T value) {
        this.value = value;
        adjacent = new ArrayList<>();
        cost = Double.POSITIVE_INFINITY;
        predictedCost = Double.POSITIVE_INFINITY;
        distanceFunction = (x) -> (0.0d);
    }

    public T getValue() {
        return value;
    }


    public void addAdjacent(Edge<T> e) {
        adjacent.add(e);
    }

    public List<Edge<T>> getAdjacent() {
        return adjacent;
    }
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPredictedCost() {
        return predictedCost;
    }

    public void setPredictedCost(double predictedCost) {
        this.predictedCost = predictedCost;
    }

    /**
     * This method sets a distance function specific to the graph
     * and type of data used. If not used, dummy value from constructor
     * will always set this to 0.0d
     * @param func Lambda Function
     */
    public void setDistanceFunction(Function<Vertex<T>, Double> func) {
        this.distanceFunction = func;
    }

    public double getHeuristicDistance(Vertex<T> goal) {

        return distanceFunction.apply(goal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        if (Double.compare(vertex.cost, cost) != 0) return false;
        if (Double.compare(vertex.predictedCost, predictedCost) != 0) return false;
        if (!value.equals(vertex.value)) return false;
        return distanceFunction != null ? distanceFunction.equals(vertex.distanceFunction) : vertex.distanceFunction == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = value.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(predictedCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
