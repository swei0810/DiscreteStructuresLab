
import java.util.*;

/**
 * A SatSolver object is capable of checking whether or not a CNFProposition is satisfiable.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class SatSolver {

    private int numRecursiveCalls = 0;  // incremented every time a recursive call is made

    /**
     * Construct a default CNFProposition sat solver.
     */
    public SatSolver() {}

    /**
     * Returns the number of recursive calls made during execution of isSatisfiable.
     * @return number of recursive calls made in execution of isSatisfiable.
     */
    public int getSearchCost() {
        return numRecursiveCalls;
    }

    /**
     * Checks whether the proposition is satisfiable.
     * @param phi the proposition in conjunctive normal form
     * @return true if proposition is satisfiable, false otherwise
     */
    public boolean isSatisfiable(CNFProposition phi) {
        Set<Variable> variables = new HashSet<>();
        variables.addAll(phi.getVariables());
        Model model = new Model(variables);
        return isSatHelper(phi, model);
    }

    private boolean isSatHelper(CNFProposition phi, Model m) {
      numRecursiveCalls++;   // please leave this line
      //base case
      Set<Variable> variables = phi.getVariables();
      List<Variable> unassignedVariables = isVariablesAssigned(variables,m);
      System.out.println(m);
        if (unassignedVariables.size()==0){
          return evaluatesToTrue(phi.getClauses(), m);
        }else{
          Variable var = unassignedVariables.get(0);
          m.assign(var,true);
          if (isSatHelper(phi, m)){
            return true;
          }
          m.unassign(var);

          m.assign(var,false);
          if (isSatHelper(phi, m)){
            return true;
          }
          m.unassign(var);
          return false;
        }
    }

    private boolean evaluatesToTrue(Set<Clause> clauses, Model m) {
      //loop through the clauses
      if (clauses.size()>=2) {
        for(Clause c:clauses) {
          Boolean checkOrPos = false;
          Boolean checkOrNeg = false;
          List <Variable> getPositiveVariables = c.getPositiveVariables();
          List <Variable> getNegativeVariables = c.getPositiveVariables();
          //
          // System.out.println("pos: " + getPositiveVariables);
          // System.out.println("neg: " + getNegativeVariables);
          // System.out.println("all: " + c.getVariables());

          for(Variable posV:getPositiveVariables){
            if (m.getTruthValue(posV)){
              checkOrPos = true;
            }
          }

          for(Variable negV: getNegativeVariables){
            if (!m.getTruthValue(negV)){
              checkOrNeg = true;
            }
          }

          if ((checkOrNeg||checkOrPos)==false) {
            return false;
          }
        }
      }
      return true;
    }


    private List<Variable> isVariablesAssigned(Set<Variable> variables, Model m){
      ArrayList<Variable> varList = new ArrayList<>();
      for(Variable var:variables){
        if (m.isUnassigned(var))
          varList.add(var);
      }
      return varList;
    }

    public static void main(String[] args) {
      SatSolver solver = new SatSolver();
      // CNFProposition proposition = CNFProposition.fromString("(p | q) & (~p | r)");
      // boolean satisfiable = solver.isSatisfiable(proposition);
      // System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");



      CNFProposition proposition = CNFProposition.fromString("(~p) & (p)");
      boolean satisfiable = solver.isSatisfiable(proposition);
      System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");

      // proposition = CNFProposition.fromString("(p | q) & (~p | ~q) & (p | ~q) & (~p | q)");
      // satisfiable = solver.isSatisfiable(proposition);
      // System.out.println("proposition = " + proposition + " " + (satisfiable? "is": "is not") + " satisfiable.");
    }
}
