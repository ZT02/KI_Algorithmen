package uebung07.src.de.hsco.ki1.hillclimbing;

public class main {
    public static void main(String[] args) {
        NQueensProblem prob1 = new NQueensProblem(8, new int[] {5,3,1,8,8,6,8,1});
        NQueensProblem prob2 = new NQueensProblem(8, new int[] {7, 2, 6, 1, 1, 8, 5, 3});
        NQueensProblem prob3 = new NQueensProblem(8, new int[] {7, 8, 2, 6, 6, 1, 7, 2});

        //System.out.println(prob1.evaluate());
        System.out.println("\nHillClimbing\n");
        // NQueensProblem foundSolution = HillClimbing.hillClimbing(prob2);
      //  System.out.println("Best Solution: "+foundSolution);

        System.out.println("\nHillClimbingLimit\n");
        //NQueensProblem foundSolution2 = HillClimbing.hillClimbingLimit(prob1, 25);
        //NQueensProblem foundSolution3 = HillClimbing.hillClimbingLimit(prob1, 25);

       // System.out.println(foundSolution);
      //  System.out.println(foundSolution.evaluate());


        System.out.println("\nSimulatedAnnealing\n");
        //NQueensProblem foundSolution2 = HillClimbing.hillClimbingLimit(prob1, 25);

        NQueensProblem foundSolution4 = SimulatedAnnealing.SimulatedAnnealing(prob1, 100, 1.12, 5, 10);
        System.out.println("\nSimulatedAnnealingLimit\n");
       // NQueensProblem foundSolution5 = SimulatedAnnealing.SimulatedAnnealingLimit(prob3, 100, 1.12, 10);
    }
}
