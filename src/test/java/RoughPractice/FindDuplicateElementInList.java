package RoughPractice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicateElementInList {

    public static void main(String[] args) {

        List<String> fruits = Arrays.asList("Apple", "Banana", "Apple", "Orange", "Orange");

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String fruit : fruits) {
            if (!seen.add(fruit)){
                duplicates.add(fruit);
            }
        }
        System.out.println("Duplicate Elements are: " + duplicates);

    }
}
