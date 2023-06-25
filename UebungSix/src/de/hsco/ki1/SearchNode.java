package UebungSix.src.de.hsco.ki1;

public class SearchNode {

    /**
     * Special SearchNode for search algorithms to indicate that a solution was
     * not found due to a cutoff
     */
    public static final SearchNode CUT_OFF = new SearchNode(null, null, "CUT-OFF", Double.POSITIVE_INFINITY);

    private final SearchNode parent;

    private final State state;

    private final String action;



    private  double pathCosts;

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


    public void setPathCosts(double pathCosts) {
        this.pathCosts = pathCosts;
    }
    public int getDepth() {
        if( parent == null ) {
            return 0;
        } else {
            return 1 + parent.getDepth();
        }
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
}
