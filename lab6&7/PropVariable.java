// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

/**
 * An atomic proposition.
 */
public class PropVariable extends Proposition {
    private String symbol;

    public PropVariable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
        //throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public boolean isVariable() {
        return (symbol.length()==1);
        //throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof PropVariable) && symbol.equals(((PropVariable)other).symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    @Override
    public Proposition copy() {
        return new PropVariable(symbol);
    }
}
