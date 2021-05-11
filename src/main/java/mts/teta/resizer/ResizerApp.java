package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.ImageProcessor;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "resizer", mixinStandardHelpOptions = true, version = "resizer 0.0.1", description = "...")
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {

    private int resizeWidth = resizeParams[0];
    private int resizeHeight = resizeParams[1];

    private int cropWidth = cropParams[0];
    private int cropHeight = cropParams[1];
    private int cropX = cropParams[2];
    private int cropY = cropParams[3];

    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }


    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public void setResizeWidth(int resizeWidth) {
            this.resizeWidth = resizeWidth;
    }

    public void setResizeHeight(int resizeHeight) {
            this.resizeHeight = resizeHeight;
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidth = cropWidth;
    }

    public void setCropHeight(int cropHeight) {
        this.cropHeight = cropHeight;
    }

    public void setCropX(int cropX) {
        this.cropX = cropX;
    }

    public void setCropY(int cropY) {
        this.cropY = cropY;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }



    public int getResizeWidth() {
        return resizeWidth;
    }

    public int getResizeHeight() {
        return resizeHeight;
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public int getCropHeight() {
        return cropHeight;
    }

    public int getCropX() {
        return cropX;
    }

    public int getCropY() {
        return cropY;
    }
}
