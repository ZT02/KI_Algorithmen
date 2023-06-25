package Uebung_Five.src.de.hsco.ki1;

import Uebung_Five.src.de.hsco.ki1.route.GermanyRouteProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Uebung05 {
    public static void main(String[] args) {
        //b)
        GermanyRouteProblem prob1 = new GermanyRouteProblem("Hamburg", "München");
        GermanyRouteProblem prob2 = new GermanyRouteProblem("Stuttgart", "Leipzig");
        GermanyRouteProblem prob3 = new GermanyRouteProblem("Aachen", "Passau");

        System.out.println("DeepLimitedSearch");
        System.out.println("prob1");
        SearchNode solution = SearchAlgorithms.depthLimitedSearch(prob1, false, 4);
        printSolution(solution);
        System.out.println("");
        System.out.println("prob2");
        solution = SearchAlgorithms.depthLimitedSearch(prob2, false, 4);
        printSolution(solution);
        System.out.println("");
        System.out.println("prob3");
        solution = SearchAlgorithms.depthLimitedSearch(prob3, false, 4);
        printSolution(solution);

        System.out.println("");
        System.out.println("iterativeDepthlimitedSearch");
        solution = SearchAlgorithms.iterativeDepthLimitedSearch(prob1, true);
        System.out.println("");
        System.out.println("prob1");
        printSolution(solution);
        System.out.println("");
        System.out.println("prob2");
        solution = SearchAlgorithms.iterativeDepthLimitedSearch(prob2, true);
        printSolution(solution);
        System.out.println("");
        System.out.println("prob3");
        solution = SearchAlgorithms.iterativeDepthLimitedSearch(prob3, true);
        printSolution(solution);

        System.out.println("");
        System.out.println("UniformCost");
        solution = SearchAlgorithms.uniformCost(prob1);
        System.out.println("");
        System.out.println("prob1");
        printSolution(solution);
        solution = SearchAlgorithms.uniformCost(prob2);
        System.out.println("");
        System.out.println("prob2");
        printSolution(solution);
        solution = SearchAlgorithms.uniformCost(prob3);
        System.out.println("");
        System.out.println("prob3");
        printSolution(solution);

        System.out.println("");
        System.out.println("GreedyBestFirstSearch");
        solution = SearchAlgorithms.greedyBestFirstSearch(prob1);
        System.out.println("");
        System.out.println("prob1");
        printSolution(solution);
        solution = SearchAlgorithms.greedyBestFirstSearch(prob2);
        System.out.println("");
        System.out.println("prob2");
        printSolution(solution);
        solution = SearchAlgorithms.greedyBestFirstSearch(prob3);
        System.out.println("");
        System.out.println("prob3");
        printSolution(solution);

//        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
//        GermanyRouteProblem prob = new GermanyRouteProblem("Stuttgart", "Leipzig");
//
//        SearchNode solution = SearchAlgorithms.breadthFirstSearch(prob, true);
//        SearchNode solution = SearchAlgorithms.depthFirstSearch(prob);
//        SearchNode solution = SearchAlgorithms.depthFirstSearch(prob, 4);
//        SearchNode solution = SearchAlgorithms.iterativeDFS(prob);

        // Uniform Cost Search
//        SearchNode solution = SearchAlgorithms.bestFirstSearch(prob, node -> node.getPathCost());

        // Greedy Best-First Search
//        SearchNode solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> prob.getDistance(node.getState(), prob.getGoalState()));

        // A* Search
//        SearchNode solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> node.getPathCost() + ???);

//        printSolution(solution);


        //        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");


    }

    public static void printSolution(SearchNode solution) {
        List<SearchNode> nodes = new ArrayList<>();
        SearchNode curr = solution;
        while(curr != null ) {
            nodes.add(curr);
            curr = curr.getParent();
        }

        Collections.reverse(nodes);

        for( SearchNode n : nodes ) {
            //System.out.println(n.getAction() + " -> " + n.getState());
            System.out.println(n.getPathCost() + " -> " + n.getState());
        }
    }
}
