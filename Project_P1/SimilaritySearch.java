/*
 * Martin Patrouchev - 300286634
 * Adam Barefoot - 300311556
 */

import java.util.*;
import java.io.*;

public class SimilaritySearch {
    public static void main(String[] args) {
        // Get arguements 
        String imagePath = args[0];
        String dataset = args[1];

        // Create a ColorHistogram object for the image
        ColorImage image = new ColorImage(imagePath);
        image.reduceColor(3);
        ColorHistogram imageHistogram = new ColorHistogram(3);
        imageHistogram.setImage(image);
        imageHistogram.normalize();

        // Create a HashMap to store the distance between the image and the other images
        Map<String, Double> imageDistance = new HashMap<>();

        // Iterate through all the images in the dataset
        File dir = new File(dataset);
        File[] directoryListing = dir.listFiles();
        Double distance;
        for (File child : directoryListing) {
            String fileName = child.getName();
            if (fileName.contains(".txt")) {
                ColorHistogram other = new ColorHistogram(3, dataset + "/" + fileName);
                other.normalize();
                distance = imageHistogram.compare(other);
                imageDistance.put(fileName, distance);
            }
        }

        // Sort the HashMap by value
        LinkedList<Map.Entry<String, Double>> list = new LinkedList<>(imageDistance.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Print the top 5 closest images
        System.out.println("The 5 most similar images are:");
        int count = 1;
        for (int i = list.size()-1; i >= list.size()-5; i--) {
            System.out.println(count + ": " + list.get(i).getKey() + " with a distance of " + list.get(i).getValue());
            count++;
        }

        
    }
}
