package tools4free.ssm;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Locale.US;
import static tools4free.ssm.SsdSlowMark.echoLn;

public class TestWriter extends TestCase {
    List<File> createdFiles = new ArrayList<>(500);

    public TestWriter(Config config) {
        super("Write", config, new File(config.out), null);
    }

    @Override
    public void run() {
        if( !doWaitFor() )
            return;

        root = new File(config.out);
        File[] files = root.listFiles();

        // cleanup from previous run
        if( files != null ) {
            for( File file : files ) {
                String fn = file.getName();
                if( fn.contains("file-") && fn.endsWith(".bin") )
                    file.delete();
            }
        }

        byte[] data = new byte[config.bs * SsdSlowMark.KB];
        Random rnd = new Random();
        float blockSizeMb = config.bs / (float)SsdSlowMark.KB;
        long fileSizeLim = (long)config.fs * SsdSlowMark.MB;

        for( int i = 0; i < data.length; i++ ) {
            data[i] = (byte)rnd.nextInt();
        }

        File file = null;

        echoLn("");
        echoLn("Files writer:");
        echoLn("       File count: %s", config.fc);
        echoLn("       File size: %s MB", config.fs);
        echoLn("       Block size: %.1f MB", config.bs / 1024.0);
        echoLn("       Root dir: %s", root.getAbsolutePath());
        echoLn("       Disk model: %s", diskModel);
        echoLn("--------------------------------------------------------------------------------------------------------------------------------------------------");

        startTime = System.currentTimeMillis();
        try {
            root.mkdirs();
            blocks = new float[config.fc * (1 + (int)(((long)config.fs * SsdSlowMark.MB) / (config.bs * SsdSlowMark.KB)))];
            for( int i = 1; !stop && i <= config.fc; i++ ) {
                long fileStarted = System.nanoTime();
                long fileMB = 0;
                long freeSpace = root.getFreeSpace();
                float perfMin = Float.MAX_VALUE, perfMax = Float.MIN_VALUE;

                if( freeSpace - fileSizeLim < SsdSlowMark.GB ) {
                    echoLn("  Abort, free space: %.1f", freeSpace / (float)SsdSlowMark.GB);
                    break;
                }

                file = new File(root, String.format(US, "file-%06d.bin", i));
                try( FileOutputStream fos = new FileOutputStream(file) ) {
                    createdFiles.add(file);

                    for( long fs = 0; !stop && fs < fileSizeLim; fs += data.length) {
                        long started = System.nanoTime();
                        {
                            fos.write(data);
                            fos.flush();
                            fos.getFD().sync();
                        }
                        long finished = System.nanoTime();

                        float sec = (finished - started) / SsdSlowMark.NANO_SEC;
                        float perfBlock = blockSizeMb / sec;

                        perfMin = min(perfMin, perfBlock);
                        perfMax = max(perfMax, perfBlock);
                        blocks[cBlocks++] = perfBlock;
                        fileMB += data.length;
                    }
                }

                printPerf("Write", file, fileStarted, fileMB, perfMin, perfMax);
                echoLn("");
            }
        }
        catch( Exception e ) {
            System.err.println("Failed to write to: " + file);
            e.printStackTrace();
        }

        stopTime = System.currentTimeMillis();
        elapsedMs = stopTime - startTime;

        echoLn("--------------------------------------------------------------------------------------------------------------------------------------------------");
        echoLn("Write test complete");
        echoLn("================");

        finished = true;
    }

    public void cleanup() {
        for( File file : createdFiles ) {
            file.delete();
        }
    }
}
