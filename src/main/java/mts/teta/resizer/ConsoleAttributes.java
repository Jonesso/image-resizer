package mts.teta.resizer;


import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

/**
 * @author Sofia Pavlinina
 */
public class ConsoleAttributes {

    @Option(names = "--resize", arity = "2", paramLabel = "width height", split = " ", description = "resize the image")
    protected int[] resizeParams = {0, 0};

    @Option(names = "--quality", defaultValue = "100", paramLabel = "value", description = "JPEG/PNG compression level")
    protected int quality;

    @Option(names = "--crop", arity = "4", paramLabel = "width height x y", split = " ", description = "сut out one or more rectangular regions of the image")
    protected int[] cropParams = {0, 0, 0, 0};

    @Option(names = "--blur", paramLabel = "{radius}", description = "reduce image noise and reduce detail levels")
    protected int blurRadius;

    @Option(names = "--format", paramLabel = "\"outputFormat\"", description = "the image format type")
    protected String outputFormat;

    @Parameters(index = "0", paramLabel = "input-file")
    protected File inputFile;

    @Parameters(index = "1..*", paramLabel = "output-file")
    protected File outputFile;


    public int[] getResizeParams() {
        return resizeParams;
    }

    public int getQuality() {
        return quality;
    }

    public int[] getCropParams() {
        return cropParams;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
