package uebung07.src.de.hsco.ki1.hillclimbing;

import java.util.Collection;

public interface ProblemState<P extends ProblemState<P>> {
	Collection<P> getSuccessors();
	double evaluate();
}
