package tools4free.ssm;

import kaiohsg.gui.Gui;

public class Config {

    String test = "rw";     // r | w | rw

    int bs = 8192;          // KB, block size
    int fs = 1024;          // MB, size of one output file
    int fc;                 // number of generated files

    String dump = "dump";   // directory to generate output and read input file
    String res = "Results"; // base name for output folder

    int iw = 800;           // px, width of the output image
    int ih = 600;           // px, height of the output image
    int ip = 60;            // px, padding of the image

    public Config fromArgs(String[] args) throws InterruptedException {
        if (args.length < 1){
            Gui gui = new Gui();

            SsdSlowMark.showGui = true;

            while (!gui.startSsm)
                Thread.sleep(0);

            fc = gui.fileCount;
            fs = gui.fileSize;
            bs = gui.blockSize;

            dump = gui.dumpFolder;
            res = "./" + gui.resultFolder;

            iw = gui.imageWidth;
            ih = gui.imageHeight;
            ip = gui.imagePadding;

            test = gui.testType;
        }

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
                case "dump":    dump = value; break;

                case "res":     res = value; break;
                case "iw":      iw = Integer.parseInt(value); break;
                case "ih":      ih = Integer.parseInt(value); break;
                case "ip":      ip = Integer.parseInt(value); break;

                case "gui":     SsdSlowMark.showGui = Boolean.parseBoolean(value); break;

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
