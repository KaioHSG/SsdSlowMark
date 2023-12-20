package tools4free.ssm;

import kaiohsg.gui.Gui;

public class Config {

    String test  = "rw";        // r | w | rw

    int bs       = 8 * 1024;    // KB, block size
    int fs       = 1 * 1024;    // MB, size of one output file
    int fc       = 1000;          // number of generated files

    String out   = "dump";     // directory to generate output files
    String in    = "dump";     // directory to read input file
    String rpt   = "./";        // base name for output folder

    int iw       = 800;         // px, width of the output image
    int ih       = 600;         // px, height of the output image
    int ip       = 60;          // px, padding of the image

    public Config fromArgs(String[] args) throws InterruptedException {

        Gui gui = new kaiohsg.gui.Gui();

        while (!gui.startSsm)
            Thread.sleep(0);

        fc = gui.fileCount;
        fs = gui.fileSize;
        bs = gui.blockSize;

        out = gui.dumpFolder;
        in = gui.dumpFolder;
        rpt = gui.resultFolder;

        iw = gui.imageWidth;
        ih = gui.imageHeight;
        ip = gui.imagePadding;

        test = gui.testType;

        Thread.sleep(500);

        for( String arg : args ) {
            int pos = arg.indexOf('=');
            if( pos == -1 )
                SsdSlowMark.exit(1, "Invalid arg: " + arg);

            String name = arg.substring(0, pos);
            String value = arg.substring(pos + 1);

            switch( name ) {
                case "test":    test = value; break;
                case "bs":      bs = Integer.parseInt(value); break;
                case "fs":      fs = Integer.parseInt(value); break;
                case "fc":      fc = Integer.parseInt(value); break;
                case "out":     out = value; break;
                case "in":      in = value; break;

                case "rpt":     rpt = value; break;
                case "iw":      iw = Integer.parseInt(value); break;
                case "ih":      ih = Integer.parseInt(value); break;
                case "ip":      ip = Integer.parseInt(value); break;

                default:        SsdSlowMark.exit(1, "Unsupported arg: " + value);
            }
        }

        if( bs < 4 || bs > (1024 * 1024) )
            SsdSlowMark.exit(1, "Invalid bs: " + bs);

        if( fs < 1 || fs > (32 * 1024) )
            SsdSlowMark.exit(1, "Invalid fs: " + fs);

        if( fc < 1 || fc > (10000) )
            SsdSlowMark.exit(1, "Invalid fc: " + fc);

        switch( test ) {
            case "r":
            case "rw":
            case "w":
            case "agg":
                break;
            default:   SsdSlowMark.exit(1, "Unsupported test: " + test);
        }

        return this;
    }
}
