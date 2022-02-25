package com.xingray.graalvm;

import com.xingray.graalvm.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;

public class FxApplication extends Application {

    public static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        File logDirectory = new File("log");
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }
        File outFile = new File(logDirectory, "out.log");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        File errorFile = new File(logDirectory, "error.log");
        if (!errorFile.exists()) {
            errorFile.createNewFile();
        }

        System.setOut(new PrintStream(outFile));
        System.setErr(new PrintStream(errorFile));
        System.out.println("System init ....");

        FXMLLoader fxmlLoader = new FXMLLoader(FxApplication.class.getResource("/page/MainController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("javafx with fxml");
        stage.setScene(scene);
        stage.show();

        MainController.afterUiShow("应用启动用时：" + (System.currentTimeMillis() - START_TIME) / 1000.0 + "s");
    }
}
