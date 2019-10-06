import java.util.*;
import java.util.stream.Collectors;

/**
 * A Clause object represents a clause in a CNFProposition proposition.  Thus, it represents a disjunction of literals.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Clause {

    private final Set<Literal> literals = new HashSet<>();

    public Clause(Collection<Literal> literals) {
        if (literals.size() == 0) {
            throw new RuntimeException("Must contain at least one literal!");
        }
        this.literals.addAll(literals);
    }

    public List<Variable> getVariables() {
        return getVariables(null);
    }

    public List<Variable> getPositiveVariables() {
        return getVariables(true);
    }

    public List<Variable> getNegativeVariables() {
        return getVariables(false);
    }

    private List<Variable> getVariables(Boolean getPositive) {
        // if getPositive is null, it returns all variables (positive and negative)
        return literals.stream().
                filter(l -> (getPositive == null || (getPositive ? l.isPositive() : l.isNegative()))).
                map(l -> l.getVariable()).
                collect(Collectors.toList());
    }

    // ---- implementation of relationToString to make debugging easier

    /**
     * Produce the clause in string form.  Uses the symbol "|" to indicate the OR of literals, as in p | q, for p OR q.
     * @return the clause in string form
     */
    @Override
    public String toString() {
        // if you get a compiler error here, you are not running Java 8!
        return "(" +
                literals.stream()
                        .map(i -> i.toString())
                        .collect(Collectors.joining(" | "))
                + ")";
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Clause)) {
            return false;
        }
        Clause other = (Clause) obj;
        return this.literals.equals(other.literals);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Literal literal : literals) {
            hash += 31*hash + literal.hashCode();
        }
        return hash;
    }
}
