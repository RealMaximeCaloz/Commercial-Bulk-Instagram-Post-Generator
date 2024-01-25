// Import the relevant libraries
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommercialPostGenerator {
    
    public static void main(String[] args) {

        System.out.println("Welcome to the Commercial Post Generator. Note: best results will be with 900x900 images.");

        // Array of quotes that can be added to each image. Each entry in the array will be added to 1 image.
        String[] quotes = {
        "The only way to do great work is to love what you do. Do what you love and you'll be happier than if you are doing something you hate. That's simple. It is not hard. Yes, this is a very long quote just to test how well the text writes on multiple lines. - Joe Doe",
        "Believe you can and you're halfway there. - Theodore Roosevelt",
        "Your time is limited, don't waste it living someone else's life. - Steve Jobs",
        "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "Strive not to be a success, but rather to be of value. - Albert Einstein",
        "The only place where success comes before work is in the dictionary. - Vidal Sassoon",
        "In the middle of difficulty lies opportunity. - Albert Einstein",
        "You miss 100% of the shots you don't take. - Wayne Gretzky",
        "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
        "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",
        "Success is stumbling from failure to failure with no loss of enthusiasm. - Winston Churchill",
        "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
        "Do not wait to strike till the iron is hot, but make it hot by striking. - William Butler Yeats",
        "It does not matter how slowly you go, as long as you do not stop. - Confucius",
        "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
        "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
        "It always seems impossible until it's done. - Nelson Mandela",
        "Don't count the days, make the days count. - Muhammad Ali",
        "The best way to predict the future is to create it. - Peter Drucker",
        "I find that the harder I work, the more luck I seem to have. - Thomas Jefferson",
        "Aim for the moon. If you miss, you may hit a star. - W. Clement Stone",
        "What lies behind us and what lies before us are tiny matters compared to what lies within us. - Ralph Waldo Emerson",
        "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
        "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
        "Opportunities don't happen. You create them. - Chris Grosser",
        "The only impossible journey is the one you never begin. - Tony Robbins",
        "You have within you right now, everything you need to deal with whatever the world can throw at you. - Brian Tracy",
        "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
        "Success is not in what you have, but who you are. - Bo Bennett",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Do not wait; the time will never be 'just right.' Start where you stand, and work with whatever tools you may have at your command, and better tools will be found as you go along. - Napoleon Hill",
        "What we think, we become. - Buddha",
        "The purpose of our lives is to be happy. - Dalai Lama",
        "I am not a product of my circumstances. I am a product of my decisions. - Stephen R. Covey",
        "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
        "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
        "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
        "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
        "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
        "Success is not in what you have, but who you are. - Bo Bennett",
        "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",
        "You have within you right now, everything you need to deal with whatever the world can throw at you. - Brian Tracy",
        "What we think, we become. - Buddha",
        "The purpose of our lives is to be happy. - Dalai Lama"
        };

        // Choose folder where images to add text to are
        String folderPath = "dimmed_images";

        // Define a Handle Name (i.e. brand name, instagram page name, etc)
        String handle = "@MINDQUOTES";

        // Get a list of all files in the folder
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

            // Check if there are any files in the folder
            if (listOfFiles != null && listOfFiles.length > 0) {
                // Loop through each file in the folder
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        // Process each image file
                        processImage(listOfFiles[i], i + 1, quotes[i], handle);
                    }
                }
            } 
            else {
                System.out.println("No files found in the specified folder.");
            }
        System.out.println("Commerical Post Generator finished.");
    }

    // Global function which incorporates images loading, adding of text and saving of final instagram post to folder
    private static void processImage(File imageFile, int imageIndex, String quote, String handle){
        try{
            //Read the image from the file
            BufferedImage imageToEdit = loadImageToEdit(imageFile);

            // Perform the Text Addition
            BufferedImage finalPost = textAdd(imageToEdit, quote, handle);
        
            // Save the finished post to a new file
            String outputFolderPath = "final_posts";
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()){
                outputFolder.mkdirs();
            }
            File finalPostLocation = new File(outputFolder, imageIndex + ".jpg");
            ImageIO.write(finalPost, "jpg", finalPostLocation);
            System.out.println("Text addition complete. Output saved to: "+finalPostLocation.getAbsolutePath());
        } 
        catch (IOException e){
                e.printStackTrace();
        }
    }

    // Function to load the image to edit
    private static BufferedImage loadImageToEdit(File imageFile) {
        try{
            BufferedImage imageToEdit = ImageIO.read(imageFile);
            return imageToEdit;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    // Function which adds text to the image to edit
    private static BufferedImage textAdd(BufferedImage image, String textToAdd, String handle) {

        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new BufferedImage with the same dimensions and type
        BufferedImage finalPost = new BufferedImage(width, height, image.getType());

        // Create a Graphics object to draw on the image
        Graphics graphics = finalPost.getGraphics();

        // Draw the original image onto the new image
        graphics.drawImage(image, 0, 0, null);

        // Set the font, font size and color for the text
        int FONT_SIZE = 30;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE);
        Color textColor = Color.WHITE;

        graphics.setFont(font);
        graphics.setColor(textColor);

        // Calculate the position to center the text
        int textWidth = graphics.getFontMetrics().stringWidth(textToAdd);
        int x = (width - textWidth) / 2;
        int y = height / 2;

        // Make text spread on multiple lines if it is too wide for the image
        if (textWidth > 0.9 * width) {
            int numberOfLines = (int)(Math.ceil((double) textWidth / (0.9 * width)));
            int middleLine = (int)Math.floor(numberOfLines/2);
        
            int lineHeight = graphics.getFontMetrics().getHeight();
            
            String[] linesToAdd = lineSplit(textToAdd, width,textWidth,(int)numberOfLines, FONT_SIZE);
            
            for (int i = 0; i < numberOfLines; i++) {
                textWidth = graphics.getFontMetrics().stringWidth(linesToAdd[i]);
                x = (width - textWidth) / 2;
                int yOffset = y + (i-middleLine) * lineHeight;
                graphics.drawString(linesToAdd[i], x, yOffset);
            }
        }
        else {
            // Draw the text onto the image if it fits on one line
            graphics.drawString(textToAdd, x, y);
        }

        // Set the font and font size for the handle text
        int HANDLE_FONT_SIZE = 20;
        Font handleFont = new Font(Font.SANS_SERIF, Font.BOLD, HANDLE_FONT_SIZE);
        graphics.setFont(handleFont);

        // Draw the handle at the bottom of the post
        int handleWidth = graphics.getFontMetrics().stringWidth(handle);
        graphics.drawString(handle, (width/2)-(handleWidth/2), height-HANDLE_FONT_SIZE);

        // Dispose of the Graphics object to free resources
        graphics.dispose();

        return finalPost;
    }

    // Function to split overflowing text into multiple segments to add instead of overflowing on one line
    private static String[] lineSplit(String paragraphToSplit, int imageWidth, int textWidth, int numberOfLines, int FONT_SIZE){
        String[] linesToAdd = new String[numberOfLines];

        // 3630 = constant that works well
        int lineLength = (int)Math.round(3630/(2.2*FONT_SIZE));
        
        int indexAdjustCount = 0;

        for(int i=0;i<numberOfLines;i++){
            int startIndex = i*lineLength-indexAdjustCount;
            int endIndex = (i+1)*lineLength;

            indexAdjustCount=0;

            // Avoid out of bounds when searching through paragraph indexes
            endIndex = Math.min(endIndex, paragraphToSplit.length());

            // Find the last space within the specified character limit
            while(endIndex < paragraphToSplit.length() && paragraphToSplit.charAt(endIndex) != ' '){
                endIndex--;
                indexAdjustCount++;
            }

            // Extract the substring for each line
            if (startIndex < endIndex) {
                linesToAdd[i] = paragraphToSplit.substring(startIndex, endIndex).trim();
                startIndex = endIndex;
            }
            else {
                linesToAdd[i] = ""; // Empty string for invalid indices
            }
        }
        return linesToAdd;
    }
}