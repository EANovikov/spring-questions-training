package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static List<String> getHashtags(List<String> twits) {
        List<String> sortedTags = new ArrayList<>();
        Map<String,Integer> tags = new HashMap<>();
        List<String> duplicatedTags = new ArrayList<>();
        for (String twit : twits) {
            duplicatedTags.addAll(Arrays.stream(twit.split(" ")).filter(word -> word.startsWith("#"))
                    .toList());
        }
        duplicatedTags.stream().forEach(t-> {
                  if(tags.containsKey(t)){
                      tags.put(t, tags.get(t)+1);
                  } else {
                      tags.put(t, 0);
                  }
                }
                );



        return sortedTags;
    }


    public static void main(String[] args) {
        List<String> twits = new ArrayList<>();
        twits.add("#Java and #Scala are the languages of cognitive and AI development. IBM sees cognitive development is the future.");
        twits.add("Some more info on the IBM investment in #Scala and Lightbendhttp8/ibm-lightbend-join-forces-enterpri");
        twits.add("IBM and @lightbend are building an integrated platform for #Java and #Scala #cognitive app development");
        getHashtags(twits);
        System.out.println("Hello, World!");
    }
}