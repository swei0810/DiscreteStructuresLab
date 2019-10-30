// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

/**
 * A proposition of the form NOT(alpha) where alpha is proposition.
 */
public class Neg extends Proposition {

    private Proposition alpha;

    public Neg(Proposition p) {
        this.alpha = p;
    }

    @Override
    public LogicalConnective getConnective() {
        return LogicalConnective.NOT;
    }

    @Override
    public Proposition getFirst() {
        // this overrides a method in parent; see javadocs of Proposition
        return alpha;
        //throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public String toString() {
        // when phi := ~p, should return "~p"  -- no parenthesis
        // when phi is something like ~(p & q) should return "~(p & q)"  -- include parenthesis
        if(alpha.isVariable()){
          return "~"+alpha;
        }else{
          return "~("+alpha+")";
        }
        //throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public Proposition copy() {
        return new Neg(alpha.copy());
    }
}
