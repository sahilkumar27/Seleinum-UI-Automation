package RoughPractice;

import java.util.*;

public class ThreeCollectionExample {

    public static void main(String[] args){

        //Allows duplicate maintain orders
        List<String> products = Arrays.asList("iPhone", "Laptop", "Mouse");

        for (String product : products){
            System.out.print(product + " ");
        }

        System.out.println();

        //Set: stores unique values
        HashSet<String> uniqueProducts = new HashSet<>(products);
        System.out.println(uniqueProducts);

        //Map: stores key value pares
        Map<String, Object> expectedResponse = new HashMap<>();


        expectedResponse.put("id", 101);
        expectedResponse.put("name", "Sahil");
        expectedResponse.put("status", "Active");

        System.out.println(expectedResponse.get("name"));


    }
}
