package Uebung_Five.src.de.hsco.ki1;

public class SearchNode implements Comparable<SearchNode>{

    private final SearchNode parent;

    private final State state;

    private final String action;

    private final double pathCosts;

    public SearchNode(SearchNode parent, State state, String action, double pathCosts) {
        this.parent = parent;
        this.state = state;
        this.action = action;
        this.pathCosts = pathCosts;
    }

    public SearchNode getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    public String getAction() {
        return action;
    }

    public double getPathCost() {
        return pathCosts;
    }

    public String toString() {
        return (parent == null ? "[null]" : parent.getState().toString()) + " -> " + getAction() + " -> " + getState();
    }

    /**
     * Check if the given state was already visited on this path
     *
     * @param state the State
     * @return true, if the given state was already visited on this path
     */
    public boolean contains(State state) {
        // If the given state is this state, return true
        if( this.state.equals(state) ) {
            return true;
        }
        // otherwise, continue checking the parent node (if there is one)
        else if( parent != null ) {
            return parent.contains(state);
        }
        return false;
    }


    public int compareTo(SearchNode o) {
        return Double.compare(getPathCost(), o.getPathCost());
    }
}
