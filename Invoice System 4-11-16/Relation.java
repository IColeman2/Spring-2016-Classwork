package unl.cse.relations;  //DO NOT CHANGE THE PACKAGE

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A class that represents a <i>relation</i> on a <i>set</i> of (parameterized) elements
 * of type <code>T</code>.
 * 
 * @param <T>
 */
public class Relation<T> {
	
	//TODO: You need to determine how you will represent the relation and
	//         possibly the underlying set
	
	/**
	 * Creates an empty relation on an empty set
	 */
	public Relation() {
		
	}
	
	/**
	 * Creates an empty relation on the given set
	 * @param items
	 */
	public Relation(Set<T> items) {
	}
	
	/**
	 * Optional copy constructor. Should create a <i>deep copy</i> of
	 * <code>that</code> relation into <code>this</code> relation.
	 * @param that
	 */
	public Relation(Relation<T> that){
	}

	/**
	 * Returns (a new copy of) the underlying set that the relation is based on
	 * @return
	 */
	public Set<T> getRelationSet() {
		return null;
	}

	/**
	 * Return <code>true</code> if this relation is <i>reflexive</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isReflexive() {
		return false;
	}
	
	/**
	 * Return <code>true</code> if this relation is <i>symmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isSymmetric() {
		return false;
	}

	/**
	 * Return <code>true</code> if this relation is <i>asymmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isAsymmetric() {
		return false;
	}

	/**
	 * Return <code>true</code> if this relation is <i>antisymmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isAntisymmetric() {
		return false;
	}

	/**
	 * Return <code>true</code> if this relation is <i>transitive</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isTransitive() {
		return false;
	}
	
	/**
	 * Return <code>true</code> if this relation is a <i>partial order</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isPartialOrder() {
		return false;
	}

	/**
	 * Return <code>true</code> if this relation is an <i>equivalence relation</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isEquivalenceRelation() {
		return false;
	}
	
	/**
	 * Returns <code>true</code> if this relation is a <i>function</i> on the underlying
	 * set, <code>false</code> otherwise.
	 * @return
	 */
	public boolean isFunction() {
		return false;
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>surjective</i> 
	 * (an onto function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isSurjection() {
		return false;
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>injective</i> 
	 * (a one-to-one function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isInjection() {
		return false;
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>bijective</i> 
	 * (a one-to-one and onto function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isBijection() {
		return false;

	}

	/**
	 * Returns <code>true</code> if the elements in the given ordered pair (a, b) are related
	 * (that is, if a is-related-to- b).
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isRelation(T a, T b) {
		return false;

	}

	/**
	 * Adds the element <code>a</code> to the underlying set that this relation 
	 * is based on.  This method does not infer any particular relation involving 
	 * <code>a</code> and other elements in the underlying set (to add relations, 
	 * use {@link #addRelation} to add relations between elements).  If <code>a</code> 
	 * is null, an {@link IllegalArgumentException} is thrown.
	 */
	public void addElement(T a) {
		
	}
	
	/**
	 * Removes the element <code>a</code> from the underlying set that this relation
	 * is based on.  As a side-effect, removes all relations involving <code>a</code> 
	 * @param a
	 */
	public void removeElement(T a) {
		
	}
	
	/**
	 * Adds the relation between the ordered pair (a, b) to this relation.  If a or b
	 * are not contained in the underlying set that this relation is based on, calling this
	 * method will add them for you.  If either a or b are null, an {@link IllegalArgumentException}
	 * is thrown.
	 * @param a
	 * @param b
	 */
	public void addRelation(T a, T b) {
		
		if(a == null || b == null) 
			throw new IllegalArgumentException("Relation does not allow null values");
	}
	
	/**
	 * Removes the relation between the ordered pair (a, b) if it exists
	 * @param a
	 * @param b
	 */
	public void removeRelation(T a, T b) {
	}

	/**
	 * Returns a new {@link Relation} that represents the <i>reflexive closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getReflexiveClosure() {
		return null;
	}
	
	/**
	 * Returns a new {@link Relation} that represents the <i>symmetric closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getSymmetricClosure() {
		return null;

	}

	/**
	 * Returns a new {@link Relation} that represents the <i>transitive closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getTransitiveClosure() {
		return null;

	}
	
	/**
	 * Composes <code>this</code> relation with <code>r</code>.  This method has
	 * been done for you as an example of how to use the interface.  Bugs in your
	 * other methods may mean this method does not work.  It will not be tested in
	 * the grader.
	 * @param r
	 * @return
	 */
	public Relation<T> compose(Relation<T> r) {
		if(!r.getRelationSet().equals(this.getRelationSet())) {
			throw new IllegalStateException("Underlying sets do not match, cannot be composed");
		}
		Relation<T> result = new Relation<T>(r.getRelationSet());
		for(T a : this.getRelationSet()) {
			for(T c : this.getRelationSet()) {
				for(T b : this.getRelationSet()) {
					if(this.isRelation(a, b) && r.isRelation(b, c)) {
						result.addRelation(a, c);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Overridden <code>toString()</code> method that returns a string representation of this
	 * relation.  The formatting of the output string includes the underlying set and all of 
	 * the ordered pairs in the relation.  An example:
	 * <pre>
	 *   R({a, b, c}) = { (a, b), (a, c), (b, a), (c, c) }
	 * </pre>
	 */
	@Override
	public String toString() {
		return null;

	}

	public static void main(String[]args){
		
		//this is an example for how to use this class (once you've implemented its functions)
		
		LinkedHashSet<String> list = new LinkedHashSet<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		Relation<String> relate1 = new Relation<String>(list);
		relate1.addRelation("a", "b");
		relate1.addRelation("b","c");
		System.out.println(relate1.toString());
		System.out.println(relate1.isSymmetric());
		System.out.println(relate1.isTransitive());
		for(String a : relate1.getRelationSet()) {
			for(String b : relate1.getRelationSet()) {
				System.out.println("(a,b) = ("+a+","+b+") = "+relate1.isRelation(a, b));
			}
		}
		
		Relation<String> rc = relate1.getReflexiveClosure();
		System.out.println(rc);
		System.out.println(rc.isReflexive());
		for(String a : rc.getRelationSet()) {
			for(String b : rc.getRelationSet()) {
				System.out.println("(a,b) = ("+a+","+b+") = "+rc.isRelation(a, b));
			}
		}
		
		Relation<String> tc = relate1.getTransitiveClosure();
		System.out.println(tc);
		System.out.println(tc.isTransitive());
		for(String a : tc.getRelationSet()) {
			for(String b : tc.getRelationSet()) {
				System.out.println("(a,b) = ("+a+","+b+") = "+tc.isRelation(a, b));
			}
		}
		Relation<String> sc = relate1.getSymmetricClosure();
		System.out.println(sc);
		System.out.println(sc.isSymmetric());
		for(String a : sc.getRelationSet()) {
			for(String b : sc.getRelationSet()) {
				System.out.println("(a,b) = ("+a+","+b+") = "+sc.isRelation(a, b));
			}
		}

		System.out.println(relate1.toString());
		System.out.println(relate1.isSymmetric());
		System.out.println(relate1.isTransitive());
		for(String a : relate1.getRelationSet()) {
			for(String b : relate1.getRelationSet()) {
				System.out.println("(a,b) = ("+a+","+b+") = "+relate1.isRelation(a, b));
			}
		}

	}
}