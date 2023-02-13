package dev.ahmed.MyOwnProject.Utils;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @author Ahmed Bughra
 * @create 2023-02-13  7:27 PM
 */
public class AudioDownloader {

    private static final String[] ALLOWED_AUDIO_TYPES = {"mp3", "wav", "ogg", "aac", "m4a"};

    public  void mp3Downloader(String url) throws Exception {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        File audioDir = new File(System.getProperty("user.home") + "/Desktop/audioFromPage");
        if (!audioDir.exists()) {
            audioDir.mkdir();
        }
        List<String> audioUrls = getAudioUrls(url);
        for (String audioUrl : audioUrls) {
            String audioFileName = getFileNameFromUrl(audioUrl);
            if (isValidAudioType(audioFileName)) {
                downloadAudio(audioUrl, audioDir.getAbsolutePath() + "/" + audioFileName);
            }
        }
    }

    private static List<String> getAudioUrls(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements audioElements = doc.getElementsByTag("audio");
        List<String> audioUrls = new ArrayList<>();
        for (Element audioElement : audioElements) {
            String audioUrl = audioElement.absUrl("src");
            if (!audioUrl.isEmpty()) {
                audioUrls.add(audioUrl);
            }
        }
        Elements sourceElements = doc.getElementsByTag("source");
        for (Element sourceElement : sourceElements) {
            String audioUrl = sourceElement.absUrl("src");
            if (!audioUrl.isEmpty()) {
                audioUrls.add(audioUrl);
            }
        }
        Elements hrefElements = doc.select("a[href]");
        for (Element hrefElement : hrefElements) {
            String hrefUrl = hrefElement.absUrl("href");
            if (isValidAudioType(hrefUrl)) {
                audioUrls.add(hrefUrl);
            }
        }
        return audioUrls;
    }

    private static String getFileNameFromUrl(String url) {
        int lastSlashIndex = url.lastIndexOf("/");
        String fileName = url.substring(lastSlashIndex + 1);
        int queryIndex = fileName.indexOf("?");
        if (queryIndex != -1) {
            fileName = fileName.substring(0, queryIndex);
        }
        return fileName;
    }

    private static boolean isValidAudioType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        for (String allowedType : ALLOWED_AUDIO_TYPES) {
            if (fileExtension.equalsIgnoreCase(allowedType)) {
                return true;
            }
        }
        return false;
    }

    private static void downloadAudio(String audioUrl, String destFilePath) throws Exception {
        URL url = new URL(audioUrl);
        try (InputStream in = url.openStream(); OutputStream out = new FileOutputStream(destFilePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
