package mts.teta.resizer.imageprocessor;

import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Sofia Pavlinina
 */
public class ImageProcessor {

    public void processImage(BufferedImage inputImage, ResizerApp resizer) throws BadAttributesException {

        checkParams(resizer);

        if (resizer.getFormat() == null) {
            resizer.setFormat(getOutputFileExtension(resizer));
        }

        // TODO Blur & crop
//        TODO inputImage - buffered
//        MarvinImage image = MarvinImageIO.loadImage(resizer.getInputFile().getAbsolutePath());
//
//        crop(image.clone(), image, resizer.getCropParams()[0], resizer.getCropParams()[1], resizer.getCropParams()[2], resizer.getCropParams()[3]);

//        GaussianBlur gaussianBlur = new GaussianBlur();
//        gaussianBlur.load();
//        gaussianBlur.setAttribute("radius", resizer.getBlur());
//        gaussianBlur.process(image.clone(), image);
//
//        gaussianBlur(image.clone(), image, resizer.getBlur());

//        MarvinImageIO.saveImage(image, resizer.getOutputFile().getAbsolutePath());

//        BufferedImage bufImage = image.getBufferedImage();

        BufferedImage thumbnail = null;
        try {
            thumbnail = Thumbnails.of(inputImage)
                    .size(resizer.getResizeWidth(), resizer.getResizeHeight())
                    .keepAspectRatio(false)
//                    .outputQuality(resizer.getQuality()/100)
                    .outputFormat(resizer.getFormat())
                    .asBufferedImage();

//            FIXME formatName
            ImageIO.write(thumbnail, resizer.getFormat().toLowerCase(), resizer.getOutputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getOutputFileExtension(ResizerApp resizer) {
        String filename = resizer.getOutputFile().getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        return extension;
    }

    private static void checkParams(ResizerApp resizer) throws BadAttributesException {
        // TODO all tests
        if (resizer.getResizeHeight() <= 0 || resizer.getResizeWidth() <= 0
                || (resizer.getQuality() < 1 || resizer.getQuality() > 100)
                || resizer.getBlur() < 0
                || resizer.getFormat() != null && (!resizer.getFormat().equals("JPEG") || !resizer.getFormat().equals("PNG")
                || !resizer.getFormat().equals("jpeg") || !resizer.getFormat().equals("png"))
        ) {
            throw new BadAttributesException("Please check params!");
        }
    }
}
