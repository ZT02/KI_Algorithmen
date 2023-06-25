package UebungSix.src.de.hsco.ki1;

import UebungSix.src.de.hsco.ki1.route.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Uebung05 {
    public static void main(String[] args) {



        SearchNode solution = null;
/*        solution = SearchAlgorithms.breadthFirstSearch(prob, true);
        solution = SearchAlgorithms.depthFirstSearch(prob);
        solution = SearchAlgorithms.depthFirstSearch(prob, 4);
        solution = SearchAlgorithms.iterativeDFS(prob);
*/
/*        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
        GermanyRouteProblem finalProb = prob;
        // Greedy Best-First Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);
        // A* Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> node.getPathCost() + finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);

        prob = new GermanyRouteProblem("Stuttgart", "Leipzig");
        // Greedy Best-First Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);
        // A* Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> node.getPathCost() + finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);

        prob = new GermanyRouteProblem("Aachen", "Passau");
        // Greedy Best-First Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);
        // A* Search

        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> node.getPathCost() + finalProb.getDistance(node.getState(), finalProb.getGoalState()));
        printSolution(solution);



        // Uniform Cost Search
        //solution = SearchAlgorithms.bestFirstSearch(prob, node -> node.getPathCost());

        // Greedy Best-First Search
        //solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> prob.getDistance(node.getState(), prob.getGoalState()));
        //printSolution(solution);

        // A* Search
        //solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> node.getPathCost() + ???);

       // printSolution(solution); /*

 */



   /*     GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
        GermanyRouteProblem finalProb = prob;

        // A* Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> 0.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Stuttgart", "Leipzig");

        // A* Search
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> 0.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Aachen", "Passau");

        // A* Search

        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> 0.0);
        printSolution(solution); */



/*

        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
        GermanyRouteProblem finalProb = prob;

        // A* Search
        GermanyRouteProblem finalProb1 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb1.getDistance(node.getState(), finalProb1.getGoalState()))/2.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Stuttgart", "Leipzig");
        finalProb = prob;
        // A* Search
        GermanyRouteProblem finalProb2 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb2.getDistance(node.getState(), finalProb2.getGoalState()))/2.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Aachen", "Passau");
        finalProb = prob;
        // A* Search

        GermanyRouteProblem finalProb3 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb3.getDistance(node.getState(), finalProb3.getGoalState()))/2.0 );
        printSolution(solution);

        System.out.println("\nRecursive Best-First Search\n");

        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
        GermanyRouteProblem finalProb = prob;

        // A* Search
        GermanyRouteProblem finalProb1 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb1.getDistance(node.getState(), finalProb1.getGoalState()))/2.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Stuttgart", "Leipzig");
        finalProb = prob;
        // A* Search
        GermanyRouteProblem finalProb2 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb2.getDistance(node.getState(), finalProb2.getGoalState()))/2.0);
        printSolution(solution);

        prob = new GermanyRouteProblem("Aachen", "Passau");
        finalProb = prob;
        // A* Search

        GermanyRouteProblem finalProb3 = finalProb;
        solution = SearchAlgorithms.bestFirstSearch(prob, (node) -> (finalProb3.getDistance(node.getState(), finalProb3.getGoalState()))/2.0 );
        printSolution(solution);
        */
        GermanyRouteProblem prob = new GermanyRouteProblem("Hamburg", "München");
        solution = SearchAlgorithms.recursiveBestFirstSearch(prob);
        printSolution(solution);
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
