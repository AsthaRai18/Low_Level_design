package FileDownloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import snakeLadder.player;

import java.util.*;
class DownloadRequest
{
    private String fileName;
    private String urlString;
    int numSegment;
}
class Segment implements Runnable
{
    private final String url;
    private final String outputFile;
    private final int startByte;
    private final int endByte;
    private final int maxRetries = 3;

    public Segment(String url, String outputFile, int startByte, int endByte) {
        this.url = url;
        this.outputFile = outputFile;
        this.startByte = startByte;
        this.endByte = endByte;
    }
    @Override
    public void run()
    {

    }

}
class  DownloadTask implements Runnable{
    private DownloadRequest req;
    private int size;
    List<Segment>segments;
    private ExecutorService segmenExecutorService;
    @Override 
    public void run()
    {
        segmenExecutorService = Executors.newFixedThreadPool(req.numSegment);
        int segmentSize = this.size/req.numSegment;
        for(int i = 0;i<this.size;i+=segmentSize)
        {
            Segment seg = new Segment("null", "null", i,i+segmentSize);
            segments.add(seg);
            segmenExecutorService.submit(seg);
        }
    }   
}
public class DownloadManager {
    private ExecutorService executorService;
    private List<DownloadTask>downloadTasks;
    public DownloadManager(int maxConcurrentDownloads)
    {
        this.executorService = Executors.newFixedThreadPool(maxConcurrentDownloads);
    }
    public void addDownload(DownloadTask tsk)
    {
        executorService.submit(tsk);
    }
}
