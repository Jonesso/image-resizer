package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.ImageProcessor;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "resizer", mixinStandardHelpOptions = true, version = "resizer 0.0.1", description = "...")
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {

    private int resizeWidth;
    private int resizeHeight;

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
//        if (resizeParams != null) {
//            this.resizeWidth = resizeParams[0];
//        } else {
            this.resizeWidth = resizeWidth;
//        }
    }

    public void setResizeHeight(int resizeHeight) {
//        if (resizeParams != null) {
//            this.resizeHeight = resizeParams[1];
//        } else {
            this.resizeHeight = resizeHeight;
//        }
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setBlur(int blur) {
        this.blur = blur;
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

}
