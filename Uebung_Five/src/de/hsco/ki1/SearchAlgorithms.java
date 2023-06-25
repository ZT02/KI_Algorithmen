package Uebung_Five.src.de.hsco.ki1;

import Uebung_Five.src.de.hsco.ki1.route.GermanyRouteProblem;

import java.util.*;


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
//a)
    public static SearchNode depthLimitedSearch(Problem prob, boolean avoidLoops, int depthLimit) {
        List<SearchNode> frontier = new ArrayList<>();
        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove(frontier.size() - 1);
            System.out.println("Current node: " + node);
            if (prob.isGoalState(node.getState())) {
                return node;
            }

            if(depthLimit >= 0) {
                for (SearchNode child : expand(node)) {
                    State state = child.getState();
                    if (!avoidLoops || !node.contains(state)) {
                        frontier.add(child);
                    }
                }
                depthLimit--;
            }
        }
        System.out.println("No solution found");
        return null;
    }

    public static SearchNode iterativeDepthLimitedSearch(Problem prob, boolean avoidLoops) {
        List<SearchNode> frontier = new ArrayList<>();
        SearchNode node;
        int depthLimit = 0;
        do {
            node = depthLimitedSearch(prob, avoidLoops, depthLimit);
            depthLimit++;
        } while(node == null);
            return node; //gibt Lösung zurück, falls keine existiert, läuft loop unendlich lange
    }

    public static SearchNode uniformCost(Problem prob) {
        HashMap<State, Double> explored = new HashMap<>();
        PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();

        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);

        explored.put(prob.getInitialState(), start.getPathCost());
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove();

            if (prob.isGoalState(node.getState())) {
                return node;
            }
            for (SearchNode child : expand(node)) {
                State state = child.getState();

                    // Graphensuche: Bereits besuchte Zustände ignorieren
                    if (!explored.containsKey(state) || child.getPathCost() < explored.get(state)) {
                        explored.remove(state);
                        explored.put(child.getState(), child.getPathCost());
                        frontier.add(child);
                    }

            }
        }
        System.out.println("Not found");
        return null;
    }

//c)
    //Heuristikfunktion mit Haversinen Formel siehe getDistance in GermanyRouteProblem

    //nochmal nachschauen in Lösung ob der hier passt
    public static SearchNode greedyBestFirstSearch(GermanyRouteProblem prob) {
        HashMap<State, Double> explored = new HashMap<>();
        PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();

        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);

        explored.put(prob.getInitialState(), Double.valueOf(prob.getDistance(start.getState(), prob.getGoalState())));
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove();

            if (prob.isGoalState(node.getState())) {
                return node;
            }

            for (SearchNode child : expand(node)) {
                State state = child.getState();

                double childToGoal = prob.getDistance(state, prob.getGoalState());

                // Graphensuche: Bereits besuchte Zustände ignorieren
                if (!explored.containsKey(state) || childToGoal < explored.get(state)) {
                    System.out.println("swapped");
                    explored.remove(state);
                    explored.put(child.getState(), Double.valueOf(childToGoal));
                    frontier.add(child);
                }

            }
        }
        System.out.println("No Solution Found");
        return null;
    }





    public static SearchNode depthFirstSearch(Problem prob) {
        return depthFirstSearch(prob, false);
    }

    public static SearchNode depthFirstSearch(Problem prob, boolean avoidLoops) {
        List<SearchNode> frontier = new ArrayList<>();

        SearchNode start = new SearchNode(null, prob.getInitialState(), null, 0);

        if (prob.isGoalState(start.getState())) {
            return start;
        }

        frontier.add(start);

        while (!frontier.isEmpty()) {
            SearchNode node = frontier.remove(frontier.size() - 1);
            System.out.println("Current node: " + node);
            if (prob.isGoalState(node.getState())) {
                return node;
            }

            for (SearchNode child : expand(node)) {
                State state = child.getState();
                if (!avoidLoops || !node.contains(state)) {
                    frontier.add(child);
                }
            }
        }
        System.out.println("No solution found");
        return null;
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
