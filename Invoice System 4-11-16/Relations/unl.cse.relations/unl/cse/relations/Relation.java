package unl.cse.relations;  //DO NOT CHANGE THE PACKAGE

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A class that represents a <i>relation</i> on a <i>set</i> of (parameterized) elements
 * of type <code>T</code>.
 * 
 * @param <T>
 */
public class Relation<T> {
	//You need to determine how you will represent the relation and
    //         possibly the underlying set
	private LinkedHashSet<HashMap<T,T>> relation;
	private Set<T> underlyingSet;
	
	/**
	 * Creates an empty relation on an empty set
	 */
	public Relation() {
		relation = new LinkedHashSet<HashMap<T,T>>();
		underlyingSet = new HashSet<T>();
	}
	
	/**
	 * Creates an empty relation on the given set
	 * @param items
	 */
	public Relation(Set<T> items) {
		relation = new LinkedHashSet<HashMap<T,T>>();
		underlyingSet = items;
		/*Iterator<T> first = items.iterator();
		while(first.hasNext()){
			T firstItem = first.next();
			Iterator<T> second = items.iterator();
			while(second.hasNext()){
				T secondItem = second.next();
				HashMap<T, T> coordinate = new HashMap<T, T>();
				coordinate.put(firstItem, secondItem);
				relation.add(coordinate);
			}
		}*/
	}
	
	/**
	 * Optional copy constructor. Should create a <i>deep copy</i> of
	 * <code>that</code> relation into <code>this</code> relation.
	 * @param that
	 */
	public Relation(Relation<T> that){
		this.underlyingSet = that.getRelationSet();
		this.relation = that.getRelation();
	}

	/**
	 * Returns (a new copy of) the underlying set that the relation is based on
	 * @return
	 */
	public Set<T> getRelationSet(){
		Set<T> clone = new HashSet<T>();
		clone.addAll(underlyingSet);
		return clone;
	}
	
	public LinkedHashSet<HashMap<T, T>> getRelation(){
		return relation;
	}

	/**
	 * Return <code>true</code> if this relation is <i>reflexive</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isReflexive() {
		Iterator<HashMap<T,T>> reflexive = relation.iterator();
		HashMap<T, Integer> items = new HashMap<T, Integer>();
		while(reflexive.hasNext()){
			HashMap<T, T> coordinate = reflexive.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			if(!items.containsKey(key)){
				items.put(key, 0);
			}
			if(!items.containsKey(coordinate.get(key))){
				items.put(coordinate.get(key), 0);
			}
			if(key.equals(coordinate.get(key))){
				items.put(key, 1);
			}
		}
		return !items.values().contains(0) && items.keySet().equals(underlyingSet);
	}
	
	/**
	 * Return <code>true</code> if this relation is <i>symmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isSymmetric() {
		Iterator<HashMap<T,T>> symmetric = relation.iterator();
		HashMap<HashMap<T, T>, Integer> items = new HashMap<HashMap<T, T>, Integer>();
		while(symmetric.hasNext()) {
			HashMap<T, T> coordinate = symmetric.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			HashMap<T,T> reverseKey = new HashMap<T,T>();
			reverseKey.put(value, key);
			/*checks if the coordinate is already recorded, if not then puts in a new
			* record for it
			*/
			if(items.containsKey(reverseKey) || key.equals(value)){
				items.put(reverseKey, 1);
			}
			else if(!items.containsKey(coordinate)){
				items.put(coordinate, 0);
			}
			/*checks if the symmetric opposite has been recorded, don't need to check
			 * if the original is there
			 */
			
		}
		return !items.values().contains(0);
	}

	/**
	 * Return <code>true</code> if this relation is <i>asymmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isAsymmetric() {
		Iterator<HashMap<T,T>> symmetric = relation.iterator();
		ArrayList<HashMap<T, T>> items = new ArrayList<HashMap<T, T>>();
		while(symmetric.hasNext()) {
			HashMap<T, T> coordinate = symmetric.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			if(key.equals(value)){
				return false;
			}
			HashMap<T,T> reverseKey = new HashMap<T,T>();
			reverseKey.put(value, key);
			if(items.contains(reverseKey)){
				return false;
			}
			items.add(coordinate);
		}
		return true;
	}

	/**
	 * Return <code>true</code> if this relation is <i>antisymmetric</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isAntisymmetric() {
		Iterator<HashMap<T,T>> symmetric = relation.iterator();
		ArrayList<HashMap<T, T>> items = new ArrayList<HashMap<T, T>>();
		while(symmetric.hasNext()) {
			HashMap<T, T> coordinate = symmetric.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			if(!key.equals(value)){
				HashMap<T,T> reverseKey = new HashMap<T,T>();
				reverseKey.put(value, key);
				if(items.contains(reverseKey)){
					return false;
				}
				else{
					items.add(coordinate);
				}
			}
		}
		return true;
	}

	/**
	 * Return <code>true</code> if this relation is <i>transitive</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isTransitive() {
		Iterator<HashMap<T,T>> transitiveIterator = relation.iterator();
		while(transitiveIterator.hasNext()){
			HashMap<T,T> coordinate = transitiveIterator.next();
			Iterator<T> setIterator1 = coordinate.keySet().iterator();
			T key1 = setIterator1.next();
			T value1 = coordinate.get(key1);
			
			Iterator<HashMap<T, T>> setIterator2 = relation.iterator();
			while(setIterator2.hasNext()){
				HashMap<T, T> coordinateCheck = setIterator2.next();
				if(!coordinateCheck.equals(coordinate)){
					Iterator<T> check = coordinateCheck.keySet().iterator();
					T key2 = check.next();
					T value2 = coordinateCheck.get(key2);
					if(key2.equals(value1) && !this.isRelation(key1, value2)){
						return false;
					}
				}
			}
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				T firstValue = pair.getKey();
				T secondValue = pair.getValue();
				if(isRelation(firstValue, secondValue)) {
					for(Entry<T,T> pair2: coordinate.entrySet()) {
						T thirdValue = pair.getValue();
						if(pair.getKey() == secondValue) {
							if(!isRelation(firstValue, thirdValue)) {
								return false;
							}
						}
					}
				}
			}*/
		}
		return true;
	}
	
	/**
	 * Return <code>true</code> if this relation is a <i>partial order</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isPartialOrder() {
		return this.isReflexive() && this.isAntisymmetric() && this.isTransitive();
	}

	/**
	 * Return <code>true</code> if this relation is an <i>equivalence relation</i>, <code>false</code>
	 * otherwise.
	 * @return
	 */
	public boolean isEquivalenceRelation() {
		return this.isSymmetric() && this.isTransitive() && this.isReflexive();
	}
	
	/**
	 * Returns <code>true</code> if this relation is a <i>function</i> on the underlying
	 * set, <code>false</code> otherwise.
	 * @return
	 */
	public boolean isFunction() {
		/*Iterator<T> functionIterator = underlyingSet.iterator();
		while(functionIterator.hasNext()){
			T input = functionIterator.next();
			Iterator<HashMap<T, T>> relationIterator = relation.iterator();
			boolean inRelation = false;
			while(relationIterator.hasNext()){
				HashMap<T, T> coordinate = relationIterator.next();
				Iterator<T> setIterator = coordinate.keySet().iterator();
				T key = setIterator.next();
				if(key.equals(input)){
					inRelation = true;
					break;
				}
			}
			if(inRelation = false){
				return false;
			}
		}
		return true;*/
		Iterator<HashMap<T,T>> surjectionIterator = relation.iterator();
		Set<T> domain = new HashSet<T>();
		Set<T> range = new HashSet<T>();
		while(surjectionIterator.hasNext()){
			HashMap<T,T> coordinate = surjectionIterator.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			if(domain.contains(key)){
				return false;
			}
			domain.add(key);
			range.add(value);
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				domain.add(pair.getKey());
				range.add(pair.getValue());
			}*/
		}
		return domain.equals(underlyingSet);
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>surjective</i> 
	 * (an onto function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isSurjection(){
		Iterator<HashMap<T,T>> surjectionIterator = relation.iterator();
		Set<T> domain = new HashSet<T>();
		Set<T> range = new HashSet<T>();
		while(surjectionIterator.hasNext()){
			HashMap<T,T> coordinate = surjectionIterator.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			domain.add(key);
			range.add(value);
		}
		return (domain.equals(underlyingSet) && range.equals(underlyingSet) && this.isFunction());
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>injective</i> 
	 * (a one-to-one function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isInjection() {
		//Because in this case domain and range are the same, this works
		Iterator<HashMap<T,T>> surjectionIterator = relation.iterator();
		Set<T> domain = new HashSet<T>();
		Set<T> range = new HashSet<T>();
		while(surjectionIterator.hasNext()){
			HashMap<T,T> coordinate = surjectionIterator.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			if(domain.contains(key) || range.contains(value)){
				return false;
			}
			domain.add(key);
			range.add(value);
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				domain.add(pair.getKey());
				range.add(pair.getValue());
			}*/
		}
		return this.isFunction();
	}

	/**
	 * Returns <code>true</code> if this relation is a function and is <i>bijective</i> 
	 * (a one-to-one and onto function), <code>false</code> otherwise.
	 * @return
	 */
	public boolean isBijection() {
		return this.isSurjection() && this.isInjection();
	}

	/**
	 * Returns <code>true</code> if the elements in the given ordered pair (a, b) are related
	 * (that is, if a is-related-to- b).
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isRelation(T a, T b) {
		HashMap<T, T> coordinate = new HashMap<T, T>();
		coordinate.put(a, b);
		return relation.contains(coordinate);

	}

	/**
	 * Adds the element <code>a</code> to the underlying set that this relation 
	 * is based on.  This method does not infer any particular relation involving 
	 * <code>a</code> and other elements in the underlying set (to add relations, 
	 * use {@link #addRelation} to add relations between elements).  If <code>a</code> 
	 * is null, an {@link IllegalArgumentException} is thrown.
	 */
	public void addElement(T a) {
		underlyingSet.add(a);
	}
	
	/**
	 * Removes the element <code>a</code> from the underlying set that this relation
	 * is based on.  As a side-effect, removes all relations involving <code>a</code> 
	 * @param a
	 */
	public void removeElement(T a) {
		underlyingSet.remove(a);
		Iterator<HashMap<T,T>> relationIterator = relation.iterator();
		HashMap<T, T> coordinate = relationIterator.next();
		while(relationIterator.hasNext()){
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			coordinate = relationIterator.next();
			if(key.equals(a) || value.equals(a)){
				relation.remove(coordinate);
			}
		}
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
		if(a == null || b == null){ 
			throw new IllegalArgumentException("Relation does not allow null values");
		}
		HashMap<T, T> coordinate = new HashMap<T, T>();
		coordinate.put(a, b);
		relation.add(coordinate);
	}
	
	/**
	 * Removes the relation between the ordered pair (a, b) if it exists
	 * @param a
	 * @param b
	 */
	public void removeRelation(T a, T b) {
		HashMap<T, T> coordinate = new HashMap<T, T>();
		coordinate.put(a, b);
		relation.remove(coordinate);
	}

	/**
	 * Returns a new {@link Relation} that represents the <i>reflexive closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getReflexiveClosure() {
		Relation<T> result = new Relation<T>(underlyingSet);
		Iterator<HashMap<T,T>> relationIterator = relation.iterator();
		Set<T> adding = result.getRelationSet();
		while(relationIterator.hasNext()){
			HashMap<T, T> coordinate = relationIterator.next();
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				T key = pair.getKey();
				T value = pair.getValue();
				if(!isRelation(key, key)){
					result.addRelation(key, key);
				}
				if(!isRelation(value, value)) {
					result.addRelation(value, value);
				}
			}*/
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			adding.remove(key);
			T value = coordinate.get(key);
			adding.remove(value);
			result.addRelation(key, value);
			if(!isRelation(key, key)){
				result.addRelation(key, key);
			}
			if(!isRelation(value, value)) {
				result.addRelation(value, value);
			}
		}
		Iterator<T> addingIterator = adding.iterator();
		while(addingIterator.hasNext()){
			T add = addingIterator.next();
			result.addRelation(add, add);
		}
		return result;
	}
	
	
	/**
	 * Returns a new {@link Relation} that represents the <i>symmetric closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getSymmetricClosure() {
		Relation<T> result = new Relation<T>(underlyingSet);
		Iterator<HashMap<T,T>> relationIterator = relation.iterator();
		while(relationIterator.hasNext()){
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				T key = pair.getKey();
				T value = pair.getValue();
				if(!isRelation(value, key)){
					result.addRelation(value, key);
				}
			}*/
			HashMap<T, T> coordinate = relationIterator.next();
			Iterator<T> setIterator = coordinate.keySet().iterator();
			T key = setIterator.next();
			T value = coordinate.get(key);
			result.addRelation(key, value);
			if(!isRelation(value, key)){
				result.addRelation(value, key);
			}
		}
		return result;
	}

	/**
	 * Returns a new {@link Relation} that represents the <i>transitive closure</i> of this
	 * relation
	 * @return
	 */
	public Relation<T> getTransitiveClosure() {
		Relation<T> result = new Relation<T>(underlyingSet);
		Iterator<HashMap<T,T>> transitiveIterator = relation.iterator();
		while(transitiveIterator.hasNext()){
			HashMap<T,T> coordinate = transitiveIterator.next();
			/*for(Entry<T,T> pair: coordinate.entrySet()) {
				T firstValue = pair.getKey();
				T secondValue = pair.getValue();
				if(isRelation(firstValue, secondValue)) {
					for(Entry<T,T> pair2: coordinate.entrySet()) {
						T thirdValue = pair.getValue();
						if(pair.getKey() == secondValue) {
							if(!isRelation(firstValue, thirdValue)) {
								result.addRelation(firstValue, thirdValue);
							}
						}
					}
				}
			}*/
			Iterator<T> setIterator1 = coordinate.keySet().iterator();
			T key1 = setIterator1.next();
			T value1 = coordinate.get(key1);
			result.addRelation(key1, value1);
			
			Iterator<HashMap<T, T>> setIterator2 = relation.iterator();
			while(setIterator2.hasNext()){
				HashMap<T, T> coordinateCheck = setIterator2.next();
				if(!coordinateCheck.equals(coordinate)){
					Iterator<T> check = coordinateCheck.keySet().iterator();
					T key2 = check.next();
					T value2 = coordinateCheck.get(key2);
					if(key2.equals(value1) && !this.isRelation(key1, value2)){
						result.addRelation(key1, value2);
					}
				}
			}
		}
		if(!result.isTransitive()){
			return result.getTransitiveClosure();
		}
		return result;
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
		StringBuilder result = new StringBuilder();
		result.append("R({");
		for(T element : underlyingSet){
			result.append(element);
			result.append(", ");
		}
		result.append("}) = { ");
		Iterator<HashMap<T,T>> stringIterator = relation.iterator();
		while(stringIterator.hasNext()){
			HashMap<T,T> coordinate = stringIterator.next();
			result.append("(");
			for(T element: coordinate.keySet()) {
				result.append(element);
			}
			result.append(", ");
			for(T element: coordinate.values()) {
				result.append(element);
			}
			result.append(")");
			if(stringIterator.hasNext()){
				result.append(",");
			}
			result.append(" ");
		}
		result.append(" }");
		return result.toString();
	}
	//private LinkedHashSet<HashMap<T,T>> relation;
	//private Set<T> underlyingSet;
	
	public static void main(String[]args){
		
		//this is an example for how to use this class (once you've implemented its functions)
		//(a, a), (b, b), (c, c), (a, b), (b, c)
		
		LinkedHashSet<String> list = new LinkedHashSet<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		Relation<String> relate1 = new Relation<String>(list);
		relate1.addRelation("a", "b");
		relate1.addRelation("b", "c");
		relate1.addRelation("c", "b");
		//relate1.addRelation("c", "a");
		//relate1.addRelation("a", "c");
		//relate1.addRelation("b", "c");
		System.out.println("Relation: " + relate1);
		System.out.println("Is Function: " + relate1.isFunction());
		/*
		System.out.println(relate1.toString());
		System.out.println(relate1.isSymmetric());
		System.out.println(relate1.isTransitive());
		System.out.println(relate1.isReflexive());
		System.out.println(relate1.isSurjection());
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
		*/
	}
}