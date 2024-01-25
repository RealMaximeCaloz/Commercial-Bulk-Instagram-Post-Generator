# Steps to use the Commercial Bulk Instagram Post Generator
## Step 1:
Load the text you wish to use in the <b>quotes</b> String[] array in CommercialPostGenerator.java. You can use ChatGPT to automate most of this step.
## Step 2
Load all the backgroudn images you wish into the images_to_crop folder. It is preferable to load images that are 900x900pixels or larger.
## Step 3:
Run BulkCropper.java. It is recommend to crop images down to 900x900pixels. This file will crop all images in the images_to_crop folder down to your requested pixel dimensions. All cropped images will be saved to the cropped_images folder.
## Step 4:
Run BulkDimmer.java. This file will dim all your images in the cropped_images folder and save them into the dimmed_images folder. This is to increase the contrast between the background image and the text to be added on each Instagram post generated.
## Step 5:
Run CommercialPostGenerator.java. This will add the text in the <b>quotes</b> String[] array to the images in the dimmed_images folder. All generated Instagram posts will be loaded into the final_posts folder.
