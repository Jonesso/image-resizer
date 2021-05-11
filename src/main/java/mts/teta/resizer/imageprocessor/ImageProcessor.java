package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static marvinplugins.MarvinPluginCollection.crop;
import static marvinplugins.MarvinPluginCollection.gaussianBlur;

/**
 * @author Sofia Pavlinina
 */
public class ImageProcessor {

    public void processImage(BufferedImage inputImage, ResizerApp resizer) throws BadAttributesException {

        checkParams(resizer);

        if (resizer.getOutputFormat() == null) {
            resizer.setOutputFormat(getOutputFileExtension(resizer));
        }


        boolean needToCrop = cropParamsPassed(resizer);

        if (resizer.getBlurRadius() > 0 || needToCrop) {

            MarvinImage image = MarvinImageIO.loadImage(resizer.getInputFile().getAbsolutePath());

            if (resizer.getBlurRadius() > 0) {
                gaussianBlur(image.clone(), image, resizer.getBlurRadius());
            }

            if (needToCrop) {
                crop(image.clone(), image, resizer.getCropX(), resizer.getCropY(), resizer.getCropWidth(), resizer.getCropHeight());
            }

            MarvinImageIO.saveImage(image, resizer.getOutputFile().getAbsolutePath());
        }

        try {
            BufferedImage imageToEdit = resizer.getOutputFile().exists() ?  ImageIO.read(resizer.getOutputFile()) : inputImage;

            Thumbnails.of(imageToEdit)
                    .size(resizer.getResizeWidth(), resizer.getResizeHeight())
                    .keepAspectRatio(false)
                    .outputQuality(resizer.getQuality() / 100)
                    .outputFormat(resizer.getOutputFormat().toLowerCase())
                    .toFile(resizer.getOutputFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkParams(ResizerApp resizer) throws BadAttributesException {

        if (resizer.getResizeHeight() <= 0 || resizer.getResizeWidth() <= 0
                || resizer.getQuality() < 1 || resizer.getQuality() > 100
                || resizer.getBlurRadius() < 0
                || resizer.getOutputFormat() != null && !isProperFormat(resizer.getOutputFormat())
                || resizer.getCropHeight() < 0 || resizer.getCropWidth() < 0 || resizer.getCropX() < 0 || resizer.getCropY() < 0
        ) {
            throw new BadAttributesException("Please check params!");
        }
    }

    private static boolean isProperFormat(String input) {
        String[] formatOptions = {"JPEG", "PNG", "jpeg", "png"};
        for (String i : formatOptions) {
            if (input.equals(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean cropParamsPassed(ResizerApp resizer) {
        return resizer.getCropHeight() != 0 && resizer.getCropWidth() != 0 && resizer.getCropX() != 0 && resizer.getCropY()!= 0;
    }


    private static String getOutputFileExtension(ResizerApp resizer) {
        String filename = resizer.getOutputFile().getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        return extension;
    }
}
