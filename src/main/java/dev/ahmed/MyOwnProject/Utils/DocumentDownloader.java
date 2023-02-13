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
/**
 * @author Ahmed Bughra
 * @create 2023-02-13  8:21 PM
 */
public class DocumentDownloader {

    private static final String[] ALLOWED_DOC_TYPES = {"pdf", "doc", "docx", "xls", "xlsx", "txt"};

    public  void downloadDocumentsFromUrl(String url) throws Exception {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        File docsDir = new File(System.getProperty("user.home") + "/Desktop/docsFromPage");
        if (!docsDir.exists()) {
            docsDir.mkdir();
        }
        List<String> docUrls = getDocumentUrls(url);
        for (String docUrl : docUrls) {
            String docFileName = getFileNameFromUrl(docUrl);
            if (isValidDocType(docFileName)) {
                downloadFile(docUrl, docsDir.getAbsolutePath() + "/" + docFileName);
            }
        }
    }

    private static List<String> getDocumentUrls(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements linkElements = doc.select("a[href]");
        List<String> docUrls = new ArrayList<>();
        for (Element linkElement : linkElements) {
            String docUrl = linkElement.absUrl("href");
            if (!docUrl.isEmpty()) {
                docUrls.add(docUrl);
            }
        }
        return docUrls;
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

    private static boolean isValidDocType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return Arrays.asList(ALLOWED_DOC_TYPES).contains(fileExtension.toLowerCase());
    }

    private static void downloadFile(String fileUrl, String destFilePath) throws Exception {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream(); OutputStream out = new FileOutputStream(destFilePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}