package uebung07.src.de.hsco.ki1.hillclimbing;

import java.util.ArrayList;
import java.util.List;

public class HillClimbing {

    public static NQueensProblem hillClimbing(NQueensProblem problem) {
       // NQueensProblem previous = problem;
        double previousEvaluation = problem.evaluate();
        double solutionEvaluation = problem.evaluate();
        NQueensProblem solution = problem;

        List<NQueensProblem> succ = new ArrayList<>(problem.getSuccessors());
        System.out.println("Durchsuche: " + succ);
        for(NQueensProblem probIterator : succ) {
            System.out.println("\tUeberpruefe: "+probIterator);
            if(probIterator.evaluate() > solutionEvaluation) {
                previousEvaluation = solutionEvaluation;
                solution = probIterator;
                solutionEvaluation = probIterator.evaluate();
                System.out.println("------------Found a better solution---------");
                System.out.println("Zustand: "+solution +" Evaluation: "+ solutionEvaluation);
            }
        }

        if(previousEvaluation!=solutionEvaluation) {
            solution = hillClimbing(solution);
        }
        return solution;
    }

    public static NQueensProblem hillClimbingLimit(NQueensProblem problem, int counter) {
        // NQueensProblem previous = problem;
        double previousEvaluation = problem.evaluate();
        double solutionEvaluation = previousEvaluation;
        NQueensProblem solution = problem;

        List<NQueensProblem> succ = new ArrayList<>(problem.getSuccessors());
        System.out.println("Durchsuche: " + succ);
        for(NQueensProblem probIterator : succ) {
            System.out.println("\tUeberpruefe: "+probIterator);
            if(probIterator.evaluate() > solutionEvaluation) {
                previousEvaluation = solutionEvaluation;
                solution = probIterator;
                solutionEvaluation = probIterator.evaluate();

                System.out.println("------------Found a better solution---------");
                System.out.println("Counter: "+counter+" Zustand: "+solution +" Evaluation: "+ solutionEvaluation);
            }
        }
        if (solutionEvaluation>previousEvaluation)
            counter = 25;



        if(counter >0)
            solution = hillClimbingLimit(solution, counter - 1);

        System.out.println("Counter: "+counter+" Zustand: "+solution +" Evaluation: "+ solutionEvaluation);
        return solution;
    }
}
