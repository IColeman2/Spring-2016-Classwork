/**
*I Coleman, Sarah Kenny, Nisha Rao
*icoleman@huskers.unl.edu / skenny@huskers.unl.edu / nisha.bookworm@gmail.com
*2/17/16
*Assignment 2.5:
*Several algorithms dealing with sets
*/

package unl.cse.sets; //DO NOT CHANGE THIS

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import unl.cse.utils.Pair;

public class SetUtils {
	
	/**
	 * example of generic set usage--this method has been done for you
	 */
	public static <T> Set<T> setMinus(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		
		result.addAll(a);
		for(T element : b) {
			result.remove(element);
		}
		return result;
	}
	
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		result.addAll(a);
		result.addAll(b);
		return result;
	}
	
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		for(T element : b) {
			if(a.contains(element)) {
				result.add(element);
			}
		}
		return result;
	}

	/**
	 * Returns a set containing all subsets of the given set
	 */
	public static <T> Set<Set<T>> getPowerSet(Set<T> set) {
		Set<Set<T>> subset = new HashSet<Set<T>>();
		if(set.isEmpty()){
			Set<T> outputSet = new HashSet<T>();
			subset.add(outputSet);
			return subset;
		}
		for(int i = 0; i < Math.pow(2, set.size()); i++){
			String binaryCode = Integer.toBinaryString(i);
			Set<T> outputSet = new HashSet<T>();
			Iterator<T> setIterator = set.iterator();
			for(int j = binaryCode.length() - 1; j >= 0; j--){
				T element = setIterator.next();
				if(binaryCode.charAt(j) == '1'){
					outputSet.add(element);
				}
			}
			subset.add(outputSet);
		}
		return subset;
	}

	/**
	 * Returns a set containing all subsets of the given set with the specified cardinality
	 */
	public static <T> Set<Set<T>> getSetsOfCardinality(Set<T> set, int size) {
		Set<Set<T>> cardinalSet = (Set<Set<T>>) new HashSet<T>();;
		Set<Set<T>> subset = getPowerSet(set);
		for(Set<T> element: subset){
			if(element.size() == size){
				cardinalSet.add(element);
			}
		}
		return cardinalSet;
	}
	
	/**
	 * Returns the symmetric difference of the two given sets
	 */
	public static <T> Set<T> symmetricDifference(Set<T> a, Set<T> b) {
		Set<T> unionSet = union(a, b);
		Set<T> intersectionSet = intersection(a, b);
		unionSet.removeAll(intersectionSet);
		return (unionSet);
	}

	public static <S, T> Set<Pair<S, T>> cartesianProduct(Set<S> a, Set<T> b) {
		Set<Pair<S, T>> result = new HashSet<Pair<S, T>>();
		for(S elementA: a){
			for(T elementB: b){
				result.add(new Pair<S, T>(elementA,elementB));
			}
		}
		return result;
	}

    public static void main(String args[]) {
	//you can use the pair class by making a pair:
	Pair<String, String> pairOfStrings = Pair.make("Hello", "World");
	
    }
	
}
