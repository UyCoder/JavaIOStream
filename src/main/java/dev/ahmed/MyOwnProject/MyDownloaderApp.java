package dev.ahmed.MyOwnProject;

import dev.ahmed.MyOwnProject.Utils.AudioDownloader;
import dev.ahmed.MyOwnProject.Utils.DocumentDownloader;
import dev.ahmed.MyOwnProject.Utils.DownloaderPics;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyDownloaderApp extends Application {
    private DownloaderPics picsDownloader = new DownloaderPics();
    private AudioDownloader audioDownloader = new AudioDownloader();
    private DocumentDownloader documentDownloader = new DocumentDownloader();

    @Override
    public void start(Stage primaryStage) {
        // Create the text input field
        TextField urlInput = new TextField("Input URL here");

        // Create the two buttons
        Button downloadPicsButton = new Button("\uD83D\uDC47 All Pictures");
        Button downloadAudioButton = new Button("\uD83D\uDC47 All Audio Files");
        Button downloadDocumengtButton = new Button("\uD83D\uDC47 All Document Files");

        // Set the event handlers for the buttons
        downloadPicsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = urlInput.getText();
                try {
                    picsDownloader.imageDownloader(url);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        downloadAudioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = urlInput.getText();
                try {
                    audioDownloader.mp3Downloader(url);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        downloadDocumengtButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = urlInput.getText();
                try {
                    documentDownloader.downloadDocumentsFromUrl(url);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Create the VBox layout and add the UI components
        VBox root = new VBox();
        root.getChildren().addAll(urlInput, downloadPicsButton, downloadAudioButton, downloadDocumengtButton);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
