package dev.ahmed.MyOwnProject.Utils;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Ahmed Bughra
 * @create 2023-02-13  1:52 AM
 *
 * This class has imageDownloader() method that can download pictures from them website.
 */
public class DownloaderPics {
    private static final String[] ALLOWED_IMAGE_TYPES = {"jpeg", "jpg", "png", "bmp", "webp", "svg", "gif"};
    private static final int MIN_IMAGE_SIZE = 30_000; // 30 KB

    public void imageDownloader(String url) throws Exception {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        File picsDir = new File(System.getProperty("user.home") + "/Desktop/picsFromPage");
        if (!picsDir.exists()) {
            picsDir.mkdir();
        }
        List<String> imgUrls = getImageUrls(url);
        for (String imgUrl : imgUrls) {
            String imgFileName = getFileNameFromUrl(imgUrl);
            if (isValidImageType(imgFileName) && isImageSizeLargerThanThreshold(imgUrl, MIN_IMAGE_SIZE)) {
                downloadImage(imgUrl, picsDir.getAbsolutePath() + "/" + imgFileName);
            }
        }
    }

    private static List<String> getImageUrls(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements imgElements = doc.getElementsByTag("img");
        List<String> imgUrls = new ArrayList<>();
        for (Element imgElement : imgElements) {
            String imgUrl = imgElement.absUrl("src");
            if (!imgUrl.isEmpty()) {
                imgUrls.add(imgUrl);
            }
        }
        return imgUrls;
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

    private static boolean isValidImageType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return Arrays.asList(ALLOWED_IMAGE_TYPES).contains(fileExtension.toLowerCase());
    }

    private static boolean isImageSizeLargerThanThreshold(String imgUrl, int threshold) throws Exception {
        URL url = new URL(imgUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("HEAD");
        conn.connect();
        int contentLength = conn.getContentLength();
        return contentLength > threshold;
    }

    private static void downloadImage(String imgUrl, String destFilePath) throws Exception {
        URL url = new URL(imgUrl);
        try (InputStream in = url.openStream(); OutputStream out = new FileOutputStream(destFilePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }





//
//
//
//    public  void imageDownloader(String url) throws Exception {
//        if (!url.startsWith("http://") && !url.startsWith("https://")) {
//            url = "https://" + url;
//        }
//        File picsDir = new File(System.getProperty("user.home") + "/Desktop/picsFromPage");
//        if (!picsDir.exists()) {
//            picsDir.mkdir();
//        }
//        List<String> imgUrls = getImageUrls(url);
//        for (String imgUrl : imgUrls) {
//            String imgFileName = getFileNameFromUrl(imgUrl);
//            if (isValidImageType(imgFileName)) {
//                downloadImage(imgUrl, picsDir.getAbsolutePath() + "/" + imgFileName);
//            }
//        }
//    }
//
//    private static List<String> getImageUrls(String url) throws Exception {
//        Document doc = Jsoup.connect(url).get();
//        Elements imgElements = doc.getElementsByTag("img");
//        List<String> imgUrls = new ArrayList<>();
//        for (Element imgElement : imgElements) {
//            String imgUrl = imgElement.absUrl("src");
//            if (!imgUrl.isEmpty()) {
//                imgUrls.add(imgUrl);
//            }
//        }
//        return imgUrls;
//    }
//
//    private static String getFileNameFromUrl(String url) {
//        int lastSlashIndex = url.lastIndexOf("/");
//        String fileName = url.substring(lastSlashIndex + 1);
//        int queryIndex = fileName.indexOf("?");
//        if (queryIndex != -1) {
//            fileName = fileName.substring(0, queryIndex);
//        }
//        return fileName;
//    }
//
//    private static boolean isValidImageType(String fileName) {
//        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//        return Arrays.asList(ALLOWED_IMAGE_TYPES).contains(fileExtension.toLowerCase());
//    }
//
//    private static void downloadImage(String imgUrl, String destFilePath) throws Exception {
//        URL url = new URL(imgUrl);
//        try (InputStream in = url.openStream(); OutputStream out = new FileOutputStream(destFilePath)) {
//            byte[] buffer = new byte[8192];
//            int bytesRead;
//            while ((bytesRead = in.read(buffer)) != -1) {
//                out.write(buffer, 0, bytesRead);
//            }
//        }
//    }
}
