package boundedtypeswildcards;

//Generic Pair class
public class Pair<T, U> {
 private T first;
 private U second;

 // Constructor to initialize the pair
 public Pair(T first, U second) {
     this.first = first;
     this.second = second;
 }

 // Getter for the first element
 public T getFirst() {
     return first;
 }

 // Getter for the second element
 public U getSecond() {
     return second;
 }

 // Setter for the first element
 public void setFirst(T first) {
     this.first = first;
 }

 // Setter for the second element
 public void setSecond(U second) {
     this.second = second;
 }

 // Method to return the reversed pair
 public Pair<U, T> reverse() {
     return new Pair<>(second, first);
 }
 
 @Override
public String toString() {
	return "Pair [first=" + first + ", second=" + second + "]";
}

public static void main(String[] args) {
     Pair<Integer, String> originalPair = new Pair<>(100, "FirstPair");

     // Print original pair
     System.out.println("Original Pair: " + originalPair);

     // Get reversed pair and print it
     Pair<String, Integer> reversedPair = originalPair.reverse();
     System.out.println("Reversed Pair: " + reversedPair);
 }

}