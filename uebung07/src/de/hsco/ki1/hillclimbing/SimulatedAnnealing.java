package uebung07.src.de.hsco.ki1.hillclimbing;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class SimulatedAnnealing {
    public static NQueensProblem SimulatedAnnealing(NQueensProblem problem, double temperatur, double temperaturCoolingRate, double temperaturMin, double energyThreshold) {
        NQueensProblem current = problem;
        NQueensProblem newSolution;
        double newEnergy;
        double currentEnergy = current.evaluate();
        double energyVariation;
        double randomNumber;
        double expO;

        do {
            newSolution =current.getSuccessors().stream().toList().get((int)(Math.random()*current.getSuccessors().size()));
            newEnergy = newSolution.evaluate();
            energyVariation = newEnergy - currentEnergy;

            if (!(energyVariation < 0)) {
                expO = Math.exp(-energyVariation / temperatur);
                randomNumber = Math.random();
                if (randomNumber < expO) {
                    current = newSolution;
                    currentEnergy = newEnergy;
                }
            } else {
                current = newSolution;
                currentEnergy = newEnergy;
                System.out.println("------------solution---------");
                System.out.println("Zustand: "+current + "-" + current.evaluate());
            }
            temperatur = temperatur / temperaturCoolingRate;
        }while(!(temperatur<temperaturMin ||currentEnergy<energyThreshold));
            //tmin und Energythreshold

        return current;
    }

    public static NQueensProblem SimulatedAnnealingLimit(NQueensProblem problem, double temperatur, double temperaturCoolingRate, int limit) {
        NQueensProblem current = problem;
        NQueensProblem newSolution;
        double newEnergy;
        double currentEnergy = current.evaluate();
        double energyVariation;
        double randomNumber;
        double expO;


        do {
            limit--;
            newSolution =current.getSuccessors().stream().toList().get((int)(Math.random()*current.getSuccessors().size()));
            newEnergy = newSolution.evaluate();
            energyVariation = newEnergy - currentEnergy;

            if (!(energyVariation < 0)) {
                expO = Math.exp(-energyVariation / temperatur);
                randomNumber = Math.random();
                if (randomNumber < expO) {
                    current = newSolution;
                    currentEnergy = newEnergy;
                }
            } else {
                current = newSolution;
                currentEnergy = newEnergy;
                System.out.println("------------solution---------");
                System.out.println("Zustand: "+current + "-" + current.evaluate());
            }
            temperatur = temperatur / temperaturCoolingRate;
        }while(limit>=0);
        //tmin und Energythreshold



        return current;
    }
}
