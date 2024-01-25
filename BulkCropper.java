// Import the relevant libraries
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class BulkCropper{
    public static void main(String[] args) {
        
        // Tool introduction to the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome to the bulk image cropper.");
        System.out.println("The bulk cropped will prompt you for the desired cropped image width and height, as well as the (x,y) coordinates from which you want to start cropping.");
        System.out.println("The crop zone starts from (x,y), and is augmented by the desired width and height.");
        System.out.println("Please note that you can only crop images down to smaller pixel dimensions.");
        System.out.println("Valid example: Cropping 1500x1420 image down to 900x900 pixels.");
        System.out.println("Invalid example: Cropping 750x900 image to 900x900 pixels.");
        System.out.println("");

        // Choose folder where the images to crop are
        String folderPath = "images_to_crop";

        // Get a list of all files in the folder
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        // Ask user for desired cropping dimensions (900x900pixels recommended)
        System.out.println("Enter the pixel width you wish to crop the image down to: ");
        int desiredWidth = scanner.nextInt();
        System.out.println("Enter the pixel height you wish to crop the image down to: ");
        int desiredHeight = scanner.nextInt();
        System.out.println("Enter the x pixel coordinate from which you wish to start cropping: ");
        int x = scanner.nextInt();
        System.out.println("Enter the y pixel coordinate from which you wish to start cropping: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Consume the spaces after nextInt

        // Check if there are any files in the folder
        if (listOfFiles != null && listOfFiles.length > 0) {
            // Loop through each file in the folder
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    // Process each image file
                    processImage(listOfFiles[i], i + 1, scanner, x, y, desiredWidth, desiredHeight);
                }
            }
        }   
        else{
            System.out.println("No files found in the specified folder.");
        }
        scanner.close();
    }

    // Global function which incorporates image loading, image cropping and saving of cropped image to folder
    private static void processImage(File imageFile, int imageIndex, Scanner scanner, int x, int y, int desiredWidth, int desiredHeight){
        try{
            //Read the image from the file
            BufferedImage originalImage = loadOriginalImage(imageFile);

            // Perform the cropping using the cropImage method
            BufferedImage croppedImage = cropImage(originalImage, x, y, desiredWidth, desiredHeight);

            // Save the cropped image to a new file
            String outputFolderPath = "cropped_images";
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()){
                outputFolder.mkdirs();
            }
            File croppedImageLocation = new File(outputFolder, imageIndex + ".jpg");
            ImageIO.write(croppedImage, "jpg", croppedImageLocation);
            System.out.println("Image manipulation complete. Output saved to: "+croppedImageLocation.getAbsolutePath());
        } 
        catch (IOException e){
                e.printStackTrace();
        }
    }

    // Function to load the original image to edit
    private static BufferedImage loadOriginalImage(File imageFile) {
        try{
            BufferedImage originalImage = ImageIO.read(imageFile);
            return originalImage;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }

    // Function which crops the original image to edit
    private static BufferedImage cropImage(BufferedImage originalImage, int x, int y, int width, int height) {
        // Check if the cropping parameters are within the bounds of the image
        if (x >= 0 && y >= 0 && x + width <= originalImage.getWidth() && y + height <= originalImage.getHeight()) {
            // Perform the crop using getSubimage
            return originalImage.getSubimage(x, y, width, height);
        } 
        else {
            // Handle invalid cropping parameters (outside image bounds)
            System.out.println("Invalid cropping parameters. The cropped region is outside the image bounds.");
            return null;
        }
    }
}



