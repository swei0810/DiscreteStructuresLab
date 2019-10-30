// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

/**
 * A proposition of the form alpha OP beta where alpha and beta are propositions and OP is a logical connective (AND,
 * OR, IMPLIES, etc).
 */
public class BinOp extends Proposition {

    private LogicalConnective op;
    private Proposition alpha;
    private Proposition beta;


    public BinOp(LogicalConnective op, Proposition p, Proposition q) {
        this.op = op;
        this.alpha = p;
        this.beta = q;
    }

    @Override
    public LogicalConnective getConnective() {
        return op;
    }

    @Override
    public Proposition getFirst() {
        return alpha;
    }

    @Override
    public Proposition getSecond() {
        return beta;
    }

    @Override
    public String toString() {
        return getSubstring(alpha) + " " + op.toString() + " " + getSubstring(beta);
    }

    // be clever about parentheses: ((p & q) & r) will be written simply as (p & q & r)
    private String getSubstring(Proposition q) {
        String pStr = "";
        if (q.isBinaryProposition()) {
            if (((BinOp)q).op.equals(this.op)) {
                pStr = q.toString();
            } else {
                pStr = "(" + q.toString() + ")";
            }
        } else {
            pStr = q.toString();
        }
        return pStr;
    }

    @Override
    public Proposition copy() {
        return new BinOp(op, alpha.copy(), beta.copy());
    }
}
