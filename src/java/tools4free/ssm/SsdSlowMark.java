package tools4free.ssm;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Formatter;

import static java.util.Locale.US;

public class SsdSlowMark {

    final static int KB = 1024;
    final static int MB = 1024 * KB;
    final static int GB = 1024 * MB;
    final static float NANO_SEC = 1_000_000_000;
    final static long testStart = System.currentTimeMillis();

    private static Config config;
    private static TestWriter writer;
    private static TestReader reader;
    private static ResultsWriter output;
    private static boolean shutdownStarted;

    static SysInfo si;
    static String ssmVersion;
    static String javaVersion;
    static String versionInfo;

    private static boolean resultsWritten;
    private static boolean statsWriting;

    public static void main(String[] args) throws Exception {

        config = new Config().fromArgs(args);

        si = new SysInfo();
        versionInfo = versionInfo();
        echoLn(versionInfo);
        echoLn("");
        switch( config.test ) {
            case "agg":
                new ResultsAggregator(config).run();
                return;
        }

        output = new ResultsWriter(config);
        System.out.println("Press ENTER to abort and generate report...");
        System.out.println("");

        if( !shutdownStarted && config.test.contains("w") )
            (writer = new TestWriter(config)).start();

        if( !shutdownStarted && config.test.contains("r") ) {
            (reader = new TestReader(config, writer)).start();
        }

        new Thread(SsdSlowMark::progressMonitor).start();

        Thread shutdownHandler = new Thread(SsdSlowMark::onShutdown);
        shutdownHandler.setName("Shutdown handler");
        Runtime.getRuntime().addShutdownHook(shutdownHandler);

        Thread.sleep(100);
        new BufferedReader(new InputStreamReader(System.in)).readLine();

        onShutdown();
    }

    private static void stopWorkers() {
        if( reader != null )
            reader.stop = true;
        if( writer != null )
            writer.stop = true;
    }

    private static final StringBuilder echoBuf = new StringBuilder();
    private static final Formatter echoFmt = new Formatter(echoBuf, US);

    public static void echo(String message) {
        System.out.print(message);
    }

    public static void echoLn(String message) {
        System.out.println(message);
    }

    public static void echo(String format, Object... args) {
        synchronized( echoBuf ) {
            echoBuf.setLength(0);
            echoFmt.format(format, args);
            echo(echoBuf.toString());
        }
    }

    public static void echoLn(String format, Object... args) {
        echo(format, args);
        echoLn("");
    }

    static String versionInfo() {
        ssmVersion = "0.2.3";
        javaVersion = javaVersion();
        return "GUI SSD Slow Mark v" + ssmVersion
                    + ", CPU: " + si.cpuModel
                    + ", MB: " + si.motherBoard
                    + ", OS: " + si.osVersion
                    + ", Java: " + javaVersion;
    }

    private static String javaVersion() {
        String javaVer = System.getProperty("java.runtime.version");
        if( javaVer == null )
            javaVer = System.getProperty("java.version");
        return javaVer != null ? javaVer : "<unknown java version>";
    }


    private static boolean writeResults() {
        if( statsWriting ) {
            while( !resultsWritten ) {
                try { Thread.sleep(10); } catch( InterruptedException e ) { /* ok */ }
            }
            return false;
        }

        statsWriting = true;
        try {
            File rptDir = null;
            if( writer != null && writer.finished )
                rptDir = output.writeTestResults(writer);
            if( reader != null && reader.finished )
                rptDir = output.writeTestResults(reader);

            output.writeSummary(versionInfo, rptDir, reader, writer);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        if( "rw".equals(config.test) )
            writer.cleanup();

        resultsWritten = true;

        return true;
    }

    private static synchronized void onShutdown() {
        if( shutdownStarted )
            return;

        shutdownStarted = true;
        stopWorkers();
        waitFinished();
        writeResults();
    }

    private static void progressMonitor() {
        waitFinished();
        if( writeResults() )
            System.exit(0);
    }

    private static void waitFinished() {
        while( (writer != null) && !writer.finished || (reader != null) && !reader.finished ) {
            try { Thread.sleep(10); } catch( InterruptedException e ) { break; }
        }
    }

    static void exit(int code, String message) {
        System.err.println(message);
        System.exit(code);
        throw new IllegalStateException("aborted");
    }


}
