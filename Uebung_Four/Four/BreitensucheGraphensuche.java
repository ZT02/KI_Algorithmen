package Uebung_Four.Four;

import java.util.HashSet;
import java.util.Set;


public class BreitensucheGraphensuche {
    public node breadth_first_search(node problem) {
        node nodeTemp = problem.initial();
        if(problem.isGoal(nodeTemp.getZustand()))
            return nodeTemp;
        frontier frontierTemp = new frontier();
        frontierTemp.Insert(nodeTemp);
        Set<node> reached = new HashSet<node>();
        reached.add(problem.initial());

        while(!frontierTemp.IsEmpty()) {
            nodeTemp = frontierTemp.Pop();
            for(node child : nodeTemp.expand()) {
                node s = child;
                if(problem.isGoal(s.getZustand()))
                    return child;
                if(!reached.contains(s)) {
                    reached.add(s);
                    frontierTemp.Insert(child);
                }
            }
        }
        return new node("failure", problem.initial(), 0, 0, null);
    }
    public node depth_first_search(node problem) {
        node nodeTemp = problem.initial();
        if(problem.isGoal(nodeTemp.getZustand()))
            return nodeTemp;
        frontier_depth frontierTemp = new frontier_depth();
        frontierTemp.Insert(nodeTemp);
        Set<node> reached = new HashSet<node>();
        reached.add(problem.initial());

        while(!frontierTemp.IsEmpty()) {
            nodeTemp = frontierTemp.Pop();
            for(node child : nodeTemp.expand()) {
                node s = child;
                if(problem.isGoal(s.getZustand()))
                    return child;
                if(!reached.contains(s)) {
                    reached.add(s);
                    frontierTemp.Insert(child);
                }
            }
        }
        return new node("failure", problem.initial(), 0, 0, null);
    }
}
