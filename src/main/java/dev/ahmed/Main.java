package dev.ahmed;

import dev.ahmed.MyOwnProject.Utils.AudioDownloader;
import dev.ahmed.MyOwnProject.Utils.DownloaderPics;

/**
 * @author Ahmed Bughra
 * @create 2023-02-13
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DownloaderPics Pics = new DownloaderPics();
        Pics.imageDownloader("https://google.com");

        AudioDownloader audio = new AudioDownloader();
        audio.mp3Downloader("https://aveclagare.org/mp3");
    }
}