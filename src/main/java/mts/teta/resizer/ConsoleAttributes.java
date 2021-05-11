package mts.teta.resizer;


import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

/**
 * @author Sofia Pavlinina
 */
public class ConsoleAttributes {

    @Option(names = "--resize", paramLabel = "width height", split = " ", description = "resize the image")
    protected int[] resizeParams;

    @Option(names = "--quality", defaultValue = "100", paramLabel = "value", description = "JPEG/PNG compression level")
    protected int quality;

    @Option(names = "--crop", paramLabel = "width height x y", split = " ", description = "сut out one or more rectangular regions of the image")
    int[] cropParams;

    @Option(names = "--blur", paramLabel = "{radius}", description = "reduce image noise and reduce detail levels")
    int blur;

    @Option(names = "--format", paramLabel = "\"outputFormat\"", description = "the image format type")
    protected String format;

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

    public int getBlur() {
        return blur;
    }

    public String getFormat() {
        return format;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
