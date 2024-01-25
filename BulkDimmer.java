// Import the relevant libraries
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BulkDimmer {
    public static void main(String[] args) {
        System.out.println("Bulk Image Dimmer start.");

        // Choose folder where images to dim are
        String folderPath = "cropped_images";

        // Get a list of all files in the folder
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        // Check if there are any files in the folder
        if (listOfFiles != null && listOfFiles.length > 0) {
            // Loop through each file in the folder
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    // Process each image file
                    processImage(listOfFiles[i], i + 1);
                }
            }
       } 
       else {
           System.out.println("No files found in the specified folder.");
       }
       System.out.println("Bulk Image Dimmer finished.");


    }

    // Global function which incorporates image loading, image dimming, and saving of dimmed image to folder
    private static void processImage(File imageFile, int imageIndex){
        try{
            //Read the image from the file
            BufferedImage croppedImage = loadCroppedImage(imageFile);

            // Perform the cropping using the cropImage method
            BufferedImage dimmedImage = dimImage(croppedImage);
        
            // Save the dimmed image to a new file
            String outputFolderPath = "dimmed_images";
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()){
                outputFolder.mkdirs();
            }
            File dimmedImageLocation = new File(outputFolder, imageIndex + ".jpg");
            ImageIO.write(dimmedImage, "jpg", dimmedImageLocation);
            System.out.println("Image dimming complete. Output saved to: "+dimmedImageLocation.getAbsolutePath());
        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // Function to load the cropped image to dim
    private static BufferedImage loadCroppedImage(File imageFile) {
        try{
            BufferedImage croppedImage = ImageIO.read(imageFile);
            return croppedImage;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    // Function which dims the cropped image
    private static BufferedImage dimImage(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new BufferedImage with the same dimensions and type
        BufferedImage dimmedImage = new BufferedImage(width, height, originalImage.getType());

        // Darken each pixel by reducing its brightness
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                // Extract the individual color components
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Darken the pixel by reducing its brightness
                double dimmingFactor = 0.4; //can be adjusted
                red = (int) (red * dimmingFactor);
                green = (int) (green * dimmingFactor);
                blue = (int) (blue * dimmingFactor);

                // Ensure that the values are in the valid range (0-255)
                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));

                // Combine the color components and set the pixel in the new image
                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                dimmedImage.setRGB(x, y, newRGB);
            }
        }
        return dimmedImage;
    }
}


