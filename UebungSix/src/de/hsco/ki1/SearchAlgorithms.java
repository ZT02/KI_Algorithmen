package UebungSix.src.de.hsco.ki1;

import UebungSix.src.de.hsco.ki1.route.GermanyRouteProblem;

import java.util.*;
import java.util.function.Function;

public class SearchAlgorithms {

    public static SearchNode breadthFirstSearch(Problem prob, boolean graphSearch) {
        HashSet<State> explored = new HashSet<>();
        List<SearchNode> frontier = new ArrayList<>();

        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);
        explored.add(start.getState());

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove(0);

            for (SearchNode child : expand(node)) {
                State state = child.getState();
                if (prob.isGoalState(state)) {
                    return child;
                }

                if (graphSearch) {
                    // Graphensuche: Bereits besuchte Zustände ignorieren
                    if (!explored.contains(state)) {
                        explored.add(state);
                        frontier.add(child);
                    }
                } else {
                    // Baumsuche: Immer den Folgezustand zur Frontier hinzufügen
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    public static SearchNode depthFirstSearch(Problem prob) {
        return depthFirstSearch(prob, false);
    }

    public static SearchNode depthFirstSearch(Problem prob, boolean avoidLoops) {
        return depthFirstSearch(prob, avoidLoops, Integer.MAX_VALUE);
    }

    public static SearchNode depthFirstSearch(Problem prob, int limit) {
        return depthFirstSearch(prob, false, limit);
    }

    public static SearchNode depthFirstSearch(Problem prob, boolean avoidLoops, int limit) {
        List<SearchNode> frontier = new ArrayList<>();

        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);

        boolean cutoffTriggered = false;
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove(frontier.size() - 1);
            System.out.println("Current node: " + node);
            if (prob.isGoalState(node.getState())) {
                return node;
            }

            if (node.getDepth() < limit) {
                for( SearchNode child : expand(node) ) {
                    State state = child.getState();
                    if( !avoidLoops || !node.contains(state) ) {
                        frontier.add(child);
                    }
                }
            } else {
                cutoffTriggered = true;
            }
        }
        if( cutoffTriggered ) {
            System.out.println("No solution found, but depth-limit was triggered");
            return SearchNode.CUT_OFF;
        }
        System.out.println("No solution found");
        return null;
    }

    public static SearchNode depthFirstSearchRecursive(Problem prob, SearchNode node) {
        if( node == null ) {
            node = new SearchNode(null, prob.getInitialState(), null, 0);
        }

        if (prob.isGoalState(node.getState())) {
            return node;
        }

        System.out.println("Current node: " + node);
        if (prob.isGoalState(node.getState())) {
            return node;
        }

        for (SearchNode child : expand(node)) {
            State state = child.getState();
            if (!node.contains(state)) {
                SearchNode solution = depthFirstSearchRecursive(prob, child);
                if( solution != null ) {
                    return solution;
                }
            }
        }
        System.out.println("No solution found");
        return null;
    }





    public static SearchNode iterativeDFS(Problem prob) {

        SearchNode solution = SearchNode.CUT_OFF;
        int limit = 0;

        while( solution == SearchNode.CUT_OFF ) {
            solution = depthFirstSearch(prob, false, limit++);
        }

        return solution;
    }

    public static SearchNode bestFirstSearch(Problem prob, Function<SearchNode, Double> f) {
        int numberGeneratedNotes = 0;
        int numberDepth = 0;
        double effectiveBrancheFactor = 0.0;
        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        HashMap<State, SearchNode> explored = new HashMap<>();
        PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>(
                (o1, o2) -> Double.compare(f.apply(o1), f.apply(o2)));

        frontier.add(start);
        explored.put(start.getState(), start);

        while (!frontier.isEmpty()) {

            for (SearchNode n : frontier ) {
                System.out.println("* " + n.getState() + ": f(n)=" + f.apply(n));
            }
            SearchNode node = frontier.remove();
            System.out.println("Expand node: " + node);
            numberDepth++;
            if (prob.isGoalState(node.getState())) {
                effectiveBrancheFactor = Math.pow(numberGeneratedNotes, 1.0 /(numberDepth+1));
                effectiveBrancheFactor = Math.pow(numberGeneratedNotes, 1.0 /(numberDepth+1));
                effectiveBrancheFactor = Math.round(effectiveBrancheFactor * 100.00);
                effectiveBrancheFactor = effectiveBrancheFactor/100.00;
                System.out.println("\nTotal Nodes: "+numberGeneratedNotes + "\nTotal Depth: "+numberDepth + "\nEffective Branching Factor: "+effectiveBrancheFactor +"\n");
                return node;
            }

            for (SearchNode child : expand(node)) {
                State state = child.getState();
                numberGeneratedNotes++;
                if (!explored.containsKey(state) || child.getPathCost() < explored.get(state).getPathCost()) {
                    if (!explored.containsKey(state)) {
                        System.out.println("Path to " + state + " (new) with cost: " + child.getPathCost());
                    } else {
                        System.out.println("Path to " + state + " (better)  with cost: " + child.getPathCost());
                    }
                    explored.put(state, child);
                    frontier.add(child);
                }
            }
        }
        effectiveBrancheFactor = Math.pow(numberGeneratedNotes, 1.0 /(numberDepth+1));
        effectiveBrancheFactor = Math.round(effectiveBrancheFactor * 100.00);
        effectiveBrancheFactor = effectiveBrancheFactor/100.00;
        System.out.println("\nTotal Nodes: "+numberGeneratedNotes + "\nTotal Depth: "+numberDepth + "\nEffective Branching Factor: "+effectiveBrancheFactor +"\n");
        return null;
    }
//fValue = pathCosts in nodes
    public static SearchNode recursiveBestFirstSearch(GermanyRouteProblem prob) {
        SearchNode solution = RBFS(prob, new SearchNode(null, prob.getInitialState(), null,  Double.POSITIVE_INFINITY),Double.POSITIVE_INFINITY);
        return solution;
    }

    public static SearchNode RBFS(GermanyRouteProblem prob, SearchNode inputNode, double f_limit) {
        SearchNode node = new SearchNode(inputNode.getParent(),inputNode.getState(), inputNode.getAction(), inputNode.getPathCost());

        if(prob.isGoalState(node.getState()))
            return node;

        List<SearchNode> successors = expand(node);

        if(successors.isEmpty())
            new SearchNode(null,null,null, Double.POSITIVE_INFINITY);

        for (SearchNode s :successors)
            s.setPathCosts( Math.max(s.getPathCost() + prob.getDistance(s.getState(), prob.getGoalState()), node.getPathCost()));

        while(true) {
            SearchNode best = successors.stream().max(Comparator.comparingDouble(SearchNode::getPathCost)).get();
            if(best.getPathCost() > f_limit)
                return new SearchNode(null,null,null, best.getPathCost());
            SearchNode alternative = successors.stream().filter(tempNode -> tempNode.getPathCost() != best.getPathCost()).max(Comparator.comparingDouble(SearchNode::getPathCost)).get();
            SearchNode result = RBFS(prob, best, Math.min(f_limit, alternative.getPathCost()));
            best.setPathCosts(result.getPathCost());

            if(result.getState() != null)
                return result;
        }
    }



    protected static List<SearchNode> expand(SearchNode node) {
        List<SearchNode> list = new ArrayList<>();

        State s = node.getState();
        Problem prob = s.getProblem();

        for (String a : prob.getActions(s)) {
            State succState = prob.getResult(s, a);
            SearchNode succ = new SearchNode(node, succState, a, node.getPathCost() + prob.getCost(s, a, succState));
            list.add(succ);
        }

        return list;
    }
}
