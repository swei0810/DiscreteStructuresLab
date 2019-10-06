import java.util.*;

/**
 * A Model represents a (partial) assignment of truth values to Variables.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Model {

    private final Set<Variable> variables;
    private final Map<Variable, Boolean> assignment;

    /**
     * Construct a model for the given collection of variables.
     * @param variables to include in the model
     */
    public Model(Set<Variable> variables) {
        this.variables = new HashSet<>();
        this.variables.addAll(variables);
        assignment = new HashMap<>();
    }

    /**
     * Assign a truth value to a given variable.
     * @param v the variable
     * @param value the truth value to assign to the variable
     */
    public void assign(Variable v, Boolean value) {
        if (!variables.contains(v)) {
            throw new RuntimeException("Variable " + v + " is not part of this model!");
        }
        if (isAssigned(v)) {
            throw new RuntimeException("Variable " + v + " already assigned!");
        }
        assignment.put(v, value);
    }

    /**
     * Unassign a truth value to a given variable.  Expects this variable to already have been assigned.
     * @param v the variable
     */
    public void unassign(Variable v) {
        if (isUnassigned(v)) {
            throw new RuntimeException("Variable " + v + " is not assigned!");
        }
        assignment.remove(v);
    }

    public boolean isAssigned(Variable v) {
        return assignment.containsKey(v);
    }

    public boolean isUnassigned(Variable v) {
        return !isAssigned(v);
    }

    public boolean getTruthValue(Variable v) {
        if (isUnassigned(v)) {
            throw new RuntimeException("Variable " + v + " is unassigned!");
        }
        return assignment.get(v);
    }

    @Override
    public String toString() {
        List<String> pairs = new ArrayList<>(assignment.size());
        for (Map.Entry<Variable, Boolean> e : assignment.entrySet()) {
            Variable v = e.getKey();
            boolean value = e.getValue();
            pairs.add(v + " -> " + (value ? "T" : "F"));
        }
        Collections.sort(pairs);
        return "Model{" + String.join(", ", pairs) + "}";
    }

}
