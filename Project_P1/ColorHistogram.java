/*
 * Martin Patrouchev - 300286634
 * Adam Barefoot - 300311556
 */
import java.lang.Math;
import java.io.*;

public class ColorHistogram {
    // Attributes
    double[] histogram;
    int d;
    ColorImage image;
    int numPixels;

    // Constructors
    // Constructor for creating a new histogram
    public ColorHistogram(int d) {
        this.d = d;
        this.histogram = new double[(int) (Math.pow(2, d*3))];
    }

    // Constructor for reading from file
    public ColorHistogram(int d, String file){
        this.d = d;
        this.histogram = new double[(int) (Math.pow(2, d*3))];

        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            this.d = d;
            
            line = bufferedReader.readLine();
            String[] values = line.split(" ");

            // Read the histogram
            int totalPixels = 0;
            for (int i = 0; i < values.length; i++){
                this.histogram[i] = Double.parseDouble(values[i]);
                totalPixels += this.histogram[i];
            }
            this.numPixels = totalPixels;

            bufferedReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // Methods
    // Getters and setters
    public double[] getHistogram() {
        return histogram;
    }

    public int getD() {
        return d;
    }

    public ColorImage getImage() {
        return image;
    }


    // Set the image and create the histogram
    public void setImage(ColorImage image) {
        this.image = image;
        this.numPixels = image.getHeight() * image.getWidth();
        // Count color occurrences and store them in the bins
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int index = (image.getPixel(j, i)[0] << (2 * d)) + (image.getPixel(j, i)[1] << d) + image.getPixel(j, i)[2];
                this.histogram[index]++;
            }
        }
    }

    // Normalize the histogram
    public void normalize() {
        for (int i = 0; i < this.histogram.length; i++) {
            this.histogram[i] /= this.numPixels;
        }
    }


    // Compare two histograms
    public double compare(ColorHistogram other) {
        double distance = 0;
        for (int i = 0; i < histogram.length; i++){
            distance += Math.min(histogram[i], other.getHistogram()[i]);
        }
        return distance;
    }
    
    // Save the histogram to a file
    public void saveColorHistogram (String filename) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            // Write the total number of bins
            bufferedWriter.write(this.histogram.length + "\n");

            // Write the histogram
            for (int i = 0; i < histogram.length; i++) {
                bufferedWriter.write(histogram[i] + " ");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
