# Commercial Bulk Instagram Post Generator
# Project Overview
This project is separated into 3 modules: 1) Bulk Image Cropper; 2) Bulk Image Dimmer; 3) Bulk Post Generator (text adder).

# Bulk Image Cropper
After running it, the Bulk Image Cropper prompts the user for the dimensions of the desired crop zone (width and height), as well as the (x,y) coordinates of the starting point of the cropzone (the top-left corner of the cropping zone).

Images fed into the Bulk Image Cropper must be smaller than the desired cropping zone. It is recommended to use a cropping zone of around 900x900pixels.

Thanks to the Bulk Image Cropper, you can load in thousands of textures into the Commercial Bulk Instragram Post Generator, without having to spend any time and energy formatting them yourself.

Down below is an example of a folder of 10 texture images that have been cropped and renamed by this software (almost instantly):

![bulk cropping example](https://github.com/RealMaximeCaloz/Portfolio/blob/659ff8ed04789975d6ebd01467d5a3e4f0e59800/BULK_CROP_EXAMPLE.png)


# Bulk Image Dimmer
Running the Bulk Image Dimmer will take each of the cropped images in the `cropped_images` folder,  extract its information, and create a deep copy of it.

The software will then iterate through each pixel of the image, extracting RGB values, and will multiply the brightness of each pixel color by a dimming factor between 0 and 1, which can be adjusted.

The dimmed RGB components are combined and saved to the dimmed image copy.

Each completely dimmed image is saved to the `dimmed_images` folder.

Down below is an example of the folder of 10 cropped images which have been bulk dimmed by this software (almost instantly):

![bulk dimming example](https://github.com/RealMaximeCaloz/Portfolio/blob/main/BULK_DIM_EXAMPLE.png)

# Bulk Post Generator (Bulk Text Add)
The Bulk Post Generator features a `quotes String[]` array which contains each string to add to each dimmed image, in order to create the final Instagram posts.

You can also define here an Instagram handle String, which can either represent your username, or your brand name. The handle will be addded to the bottom of each Instagram post to serve as a non-invasive watermark, hence giving the user of this software free publicity if the post garners attention, or is stolen by another Internet user.

The Bulk Post Generator will open each image in the `dimmed_images` folder and call the `textAdd()` method.

The `textAdd()` method gets the width and height of the dimmed_image, in order to know the area available to add text to.
The font, font size, and text color are then set.

The width of the text String to add is then calculated, and compared to the image width to determine if there is text overflow.

If there is no text overflow, the String is drawn to the dimmed image by using a graphics object, and the final post is saved into the `final_posts` folder.

If the String is determined to be so large that it would cause text overflow, then the number of required lines in which to split the String is calculated.
Furthermore, another method called `lineSplit()` is called to separate the String into the previously calculated number of required lines.

With the calculated required number of lines and the initial overflowing String separated into multiple Strings, the graphics object is used multiple times in a loop to draw each String segment, one under the previous, effectively creating a final Instagram Post with multi-line text.

Down below is an example of the folder of 10 dimmed images to which text has been added (within a few seconds), as well as the Instagram handle watermark at the very bottom:

![bulk text add example](https://github.com/RealMaximeCaloz/Portfolio/blob/main/BULK_TEXT_ADD_EXAMPLE.png)

Finally, down below is a close-up of 1 generated Instragram post:

![Generated Post Example](https://github.com/RealMaximeCaloz/Portfolio/blob/659ff8ed04789975d6ebd01467d5a3e4f0e59800/GeneratedInstagramPostExample.jpg)

Overall, this software can save any company with an Instagram presence an incredible number of hours of work.

# Installation
1. Clone this repository:
```
$ git clone https://github.com/RealMaximeCaloz/Commercial-Bulk-Instagram-Post-Generator.git
```

# How to Run
## Step 1:
Load the text you wish to use in the `quotes String[]` array in `CommercialPostGenerator.java`. You can use ChatGPT to automate most of this step.
## Step 2
Load all the background images you wish to use into the `images_to_crop` folder. It is preferable to load images that are 900x900pixels or larger.
## Step 3:
Run `BulkCropper.java`. It is recommend to crop images down to 900x900pixels. This file will crop all images in the `images_to_crop` folder down to your requested pixel dimensions. All cropped images will be saved to the `cropped_images` folder.
## Step 4:
Run `BulkDimmer.java`. This file will dim all your images in the `cropped_images` folder and save them into the `dimmed_images` folder. This is to increase the contrast between the background image and the text to be added on each Instagram post generated.
## Step 5:
Run `CommercialPostGenerator.java`. This will add the text in the `quotes String[]` array to the images in the dimmed_images folder. All generated Instagram posts will be loaded into the `final_posts` folder.
