import java.util.*;
import java.util.stream.Collectors;

/**
 * A CNFProposition object represents a proposition in conjunctive normal form (CNF).  A CNF proposition is a conjunction of one or more
 * clauses, where each clause a disjunction of one or more literals.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class CNFProposition {

    private final Set<Clause> clauses = new HashSet<>();

    public CNFProposition(Collection<Clause> clauses) {
        if (clauses.size() == 0) {
            throw new RuntimeException("Must contain at least one clause!");
        }
        this.clauses.addAll(clauses);
    }

    public Set<Clause> getClauses() {
        return clauses;
    }

    /**
     * Returns a Set that contains all of the variables that appear in any
     * clause in this proposition.
     * @return a set of Variable objects
     */
    public Set<Variable> getVariables() {
        // to implement this method, loop through the clauses, get their variables,
        // and add those variables to a set that you return
        //change list to set
        Set<Variable> variables = new HashSet<>();
        for(Clause c:clauses){
          variables.addAll(c.getVariables());
        }
        return variables;
    }

    public static void main(String[] args) {
        CNFProposition proposition = CNFProposition.fromString("(p | ~q) & (p | q)");
        System.out.println("The CNFProposition is " + proposition);
        System.out.println("It contains these variables " + proposition.getVariables());
    }

    /**
     * Factory method for CNFProposition objects.  See its usage in the main method.
     * Builds a CNFProposition by parsing the given string.  Expects proposition of the form "(p | ~q) & (p) & (~q | r)"  In other
     * words, clauses combined with & and surrounded by parenthesis.  Literals within clause are combined with | and
     * negated literals preceded by ~.
     * @param cnfStr
     * @return
     */
    public static CNFProposition fromString(String cnfStr) {
        String[] clauseStrs = cnfStr.split("\\s*&\\s*");
        List<Clause> clauses = new LinkedList<>();
        for (String clauseStr : clauseStrs) {
            if (!(clauseStr.startsWith("(") & clauseStr.endsWith(")"))) {
                throw new RuntimeException("invalid clause: expected open and close parens: '" + clauseStr + "'");
            }
            clauseStr = clauseStr.substring(1, clauseStr.length() - 1);
            if (clauseStr.contains("(") || clauseStr.contains(")")) {
                throw new RuntimeException("Invalid clause: '" + clauseStr + "'.  Clauses should not contain " +
                        "parentheses");
            }
            String[] literalStrs = clauseStr.split("\\s*\\|\\s*");
            List<Literal> literals = new LinkedList<>();
            for (String literalStr : literalStrs) {
                boolean isNegated;
                isNegated = literalStr.startsWith("~");
                String variableStr = literalStr;
                if (isNegated) {
                    variableStr = literalStr.substring(1);
                }
                Variable v = new Variable(variableStr);
                Literal literal = new Literal(v, !isNegated);
                literals.add(literal);
            }
            Clause clause = new Clause(literals);
            clauses.add(clause);
        }
        return new CNFProposition(clauses);
    }

    @Override
    public String toString() {
        return clauses.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(" & "));
    }

}
