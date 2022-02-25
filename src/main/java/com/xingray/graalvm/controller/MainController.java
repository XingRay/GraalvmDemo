package com.xingray.graalvm.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xingray.graalvm.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static MainController mainController;

    @FXML
    private TextArea output;

    @FXML
    private TextArea input;

    @FXML
    public void onActionSend(ActionEvent event) {
        var msg = this.input.getText();
        System.out.println(msg);
        this.output.setText(msg);
        this.input.requestFocus();
        this.input.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;
    }

    public static void afterUiShow(String msg) {
        System.out.println(msg);
        mainController.input.requestFocus();
        StringBuilder stringBuilder = new StringBuilder();
        Gson gson = new Gson();

        User user = new User("aaa", 13, "12123");
        String jsonString = gson.toJson(user);

        stringBuilder.append(jsonString).append("\n");

        User user1 = gson.fromJson(jsonString, User.class);
        stringBuilder.append(user1.toString()).append("\n");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        String listJsonString = gson.toJson(userList);
        stringBuilder.append(listJsonString).append("\n");

        List<User> userList2 = gson.fromJson(listJsonString, new TypeToken<List<User>>() {
        }.getType());
        stringBuilder.append(userList2.toString()).append("\n");

        mainController.output.setText(stringBuilder.toString());
    }
}