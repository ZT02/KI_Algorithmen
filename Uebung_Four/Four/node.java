package Uebung_Four.Four;

import java.util.LinkedList;

public class node {
    public String getZustand() {
        return zustand;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    String zustand;
    node parent;
    int action;
    int pathCost;

    LinkedList<node> childNodes;

    public node getParent() {
        return parent;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public node(String zustand, node parent, int action, int pathCost, LinkedList<node> childNodes) {
        this.zustand = zustand;
        this.parent = parent;
    this.action = action;
    this.pathCost = 5 + pathCost;
    this.childNodes = childNodes;
    }
    public node initial() {
        return new node(this.zustand, this.parent, this.action, this.pathCost, this.childNodes);
    }
    public boolean isGoal(String s) {
        return s.equals("ziel");
    }
    public LinkedList<node> expand() {
        LinkedList<node> temp = new LinkedList<node>();
        for(node temp2 : childNodes) {
            if (temp2 != null)
                temp.add(temp2);
        }
                return childNodes;
    }

}
