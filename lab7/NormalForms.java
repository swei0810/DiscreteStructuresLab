// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

/**
 * Static methods that rewrite a proposition into various normal forms (or otherwise simplified form).
 */
public class NormalForms {


    /**
     * Constructs a proposition psi such that (1) psi contains only the logical connectives in {&, ~} and (2)
     * psi is logically equivalent to phi.
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically equivalent to phi
     */
    public static Proposition simplify(Proposition phi) {
      Proposition psi;
      if (phi.isVariable()){
        return phi;
      }else if(phi.isNotProposition()){
        return NotSimplify(simplify(phi.getFirst()));
      }else if(phi.isAndProposition()){
        return And(simplify(phi.getFirst()),simplify(phi.getSecond()));
      }else if (phi.isOrProposition()){
        return NotSimplify(And(NotSimplify(simplify(phi.getFirst())), NotSimplify(simplify(phi.getSecond()))));
      }else if (phi.isIfProposition()){
        return NotSimplify(And(simplify(phi.getFirst()), NotSimplify(simplify(phi.getSecond()))));
      }else{
        throw new IllegalPropositionException();
      }
        // throw new UnsupportedOperationException("implement me!");
    }

    public static Proposition NotSimplify(Proposition alpha) {
      return new Neg(alpha);
    }
    public static Proposition NotNNF(Proposition alpha){
      if(alpha.isVariable()){
        return new Neg(alpha);
      }else{
        return alpha.getFirst();
      }
    }

    public static  Proposition And(Proposition alpha, Proposition beta){
      return new BinOp(LogicalConnective.AND, alpha, beta);

    }

    public static Proposition Or(Proposition alpha, Proposition Beta){
      return Build.disj(alpha,Beta);
    }

    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form  and (2) psi is logically
     * equivalent to phi.  Expects a proposition that has only & and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied only to atomic propositions (i.e.
     * variables) and not to more complex expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in NNF and logically equivalent to phi
     * @throws IllegalPropositionException if phi contains a connective that is not in the sets {&, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
     if(phi.isVariable()){
       return phi;
     }else if(phi.isNotProposition()){
        if(phi.getFirst().isVariable()){
          return phi;
        }else if(phi.getFirst().isAndProposition()){
           return Or(NotNNF(toNNF((phi.getFirst().getFirst()))),NotNNF(toNNF(phi.getFirst().getSecond())));
         }else if(phi.getFirst().isNotProposition()){
           return toNNF(phi.getFirst().getFirst());
         }else{
           throw new IllegalPropositionException("Proposition must be simplified");
         }
     }else if(phi.isAndProposition()){
       return And(toNNF(phi.getFirst()),toNNF(phi.getSecond()));
     }else{
       throw new IllegalPropositionException("Proposition must be simplified");
     }
      // throw new UnsupportedOperationException("implement me!");
    }


    /**
     * Constructs a proposition psi such that (1) psi is in CNF and (2) psi is logically
     * equivalent to phi.  Expects a proposition phi that is in NNF.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in CNF and logically equivalent to phi
     * @throws IllegalPropositionException if phi is not in NNF
     */
    public static Proposition fromNNFtoCNF(Proposition phi) {
        if(phi.isVariable()){
          return phi;
        }else if (phi.isNotProposition() && phi.getFirst().isVariable()){
          //throw exception if not nnf
          return phi;
        }else if(phi.isOrProposition()){
          return DistributeOr(fromNNFtoCNF(phi.getFirst()),fromNNFtoCNF(phi.getSecond()));
        }else if(phi.isAndProposition()){
          return And(fromNNFtoCNF(phi.getFirst()),fromNNFtoCNF(phi.getSecond()));
        }else {
          throw new IllegalPropositionException("Phi not in NNF");
        }
    }

    public static Proposition DistributeOr(Proposition alpha, Proposition beta){
      if (alpha.isVariable() && beta.isVariable()) {
        return Or(alpha, beta);
      }else if(alpha.isOrProposition() || beta.isOrProposition()){
        return Or(alpha,beta);
      }else {
        return And(Or(alpha.getFirst(), beta), Or(alpha.getSecond(), beta));
      }
    }




    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and (2) psi is logically equivalent to
     * phi.
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Proposition toCNF(Proposition phi) {
        return fromNNFtoCNF(toNNF(simplify(phi)));
        //throw new UnsupportedOperationException("implement me!");
    }

    public static void main(String[] args) {
        //~(~p) | q
        PropVariable p = new PropVariable("p");
        PropVariable q = new PropVariable("q");
        PropVariable r = new PropVariable("r");

        Proposition psi = Build.neg(Build.disj(Build.neg(Build.neg(p)),q));
        Proposition alpha = Build.neg(Build.conj(Build.neg(p), Build.neg(q)));
        Proposition alpha2 = Build.neg(Build.conj(p, q));
        Proposition beta = Build.disj(p,q);
        Proposition theta = Build.neg(Build.implies(p,q));
        Proposition gamma = Build.conj(simplify(alpha),simplify(beta));
        Proposition phi = Build.neg(Build.neg(p));
        // System.out.println(q.getFirst());

        Proposition nnf = Build.disj(Build.conj(p,q), r);
        Proposition testNNF = Build.disj(Build.disj(p,q), r);
        Proposition z = Build.neg(Build.neg(p));

        Proposition recursiveAnd = Build.neg(Build.conj(Build.neg(p), Build.neg(q)));

        // System.out.println(testNNF+": "+fromNNFtoCNF(testNNF));
        // System.out.println(nnf + ": " + fromNNFtoCNF(nnf));
        System.out.println(theta + ": " + toCNF(theta));



    }
}
