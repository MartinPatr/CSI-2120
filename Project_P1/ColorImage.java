/*
 * Martin Patrouchev - 300286634
 * Adam Barefoot - 300311556
 */
import java.io.*;

public class ColorImage {
    // Attributes
    int width;
    int height;
    int depth;
    int[][][] pixels; 

    // Constructor
    public ColorImage(String filename) {    
        // Load the image from the file
        // Set the width, height, and depth
        try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Skip first 2 lines
            bufferedReader.readLine();
            bufferedReader.readLine();

            // Read the width, height, and depth
            String line = bufferedReader.readLine();
            String[] attributes = line.split(" ");
            this.width = Integer.parseInt(attributes[0]);
            this.height = Integer.parseInt(attributes[1]);

            line = bufferedReader.readLine();
            this.depth = Integer.parseInt(line);


            // Create a 2D array to store the pixels
            this.pixels = new int[height][width][3];

            // Read the pixels
            String[] linePixles;
            int counter = 0;
            int heightCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                linePixles = line.split(" ");

                for (int j = 0; j < linePixles.length; j++) {
                    int[] pixel = new int[3];
                    pixel[0] = Integer.parseInt(linePixles[j]);
                    pixel[1] = Integer.parseInt(linePixles[++j]);
                    pixel[2] = Integer.parseInt(linePixles[++j]);

                    this.pixels[heightCounter][counter] = pixel;


                    if (counter == width-1) {
                        counter = 0;
                        heightCounter++;
                    }else{
                        counter++;
                    }
                }   
            }

            // Close the file
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods
    // Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public int[] getPixel(int x, int y) {
        return pixels[y][x];
    }

    /*
     * Reduce the color depth of the image
     * @param d The number of bits to reduce the color depth by
     */
    public void reduceColor(int d) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                // Get the number of bits required to represent the depth, and then shift the bits to the right
                int bits = (int) Math.ceil(Math.log(depth) / Math.log(2));
                pixels[i][j][0] = pixels[i][j][0] >> (bits - d);
                pixels[i][j][1] = pixels[i][j][1] >> (bits - d);
                pixels[i][j][2] = pixels[i][j][2] >> (bits - d);
            }
        }
    }
}

