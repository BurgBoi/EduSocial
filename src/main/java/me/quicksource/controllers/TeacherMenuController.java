package me.quicksource.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.quicksource.Client;
import me.quicksource.GuiUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {

    public static Stage createClassStage;
    public static Stage createResourceStage;
    public static VBox teacherClasses;
    public static VBox teacherResources;
    public static WebView teacherWebViewer;
    public static Map<String, String> loadedTeacherClasses = new HashMap<>();

    @FXML
    private WebView webViewer;

    @FXML
    private VBox classes;

    @FXML
    private VBox resources;


    public void onClassCreateClicked(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        stage.setScene(GuiUtils.getSceneFromFXML("createClassForm.fxml"));
        stage.setTitle("Create a class");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        createClassStage = stage;
        teacherClasses = classes;
    }

    public void onCreateResourceClicked(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        stage.setScene(GuiUtils.getSceneFromFXML("createResourceForm.fxml"));
        stage.setTitle("Create a resource");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        createResourceStage = stage;
        teacherResources = resources;
        teacherWebViewer = webViewer;
    }

    public void onLogout(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client client = new Client("127.0.0.1", 5000, data -> Platform.runLater(() -> {
            try {

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(data.toString());

                if (jsonObject.get("dataType").equals("user-account-data")) {

                    JSONArray classes = (JSONArray) jsonObject.get("classes");

                    for (int i = 0; i < classes.size(); i++) {

                        JSONObject classData = (JSONObject) classes.get(i);
                        Button button = new Button((String) classData.get("name"));

                        button.setOnMouseClicked(event -> {

                            Client eventClient = new Client("127.0.0.1", 5000, eventData -> Platform.runLater(() -> {
                                        try {

                                            JSONParser eventParser = new JSONParser();
                                            JSONObject eventJsonObject = (JSONObject) eventParser.parse(eventData.toString());

                                            if (eventJsonObject.get("dataType").equals("class-resources")) {

                                                JSONArray classResources = (JSONArray) eventJsonObject.get("resources");
                                                for (int index = 0; index < classResources.size(); index++) {

                                                    Button resourceButton = new Button((String) ((JSONObject) classResources.get(index)).get("name"));
                                                    int finalIndex = index;

                                                    resourceButton.setOnMouseClicked(resourceEvent -> {

                                                        WebEngine engine = webViewer.getEngine();
                                                        engine.load("http://" + ((JSONObject) classResources.get(finalIndex)).get("link"));

                                                    });

                                                    this.resources.getChildren().add(resourceButton);

                                                }

                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                            ));

                            try {

                                JSONObject request = new JSONObject();
                                request.put("request_type", "get-class-resources");
                                request.put("uuid", loadedTeacherClasses.get(event.getButton().name()));
                                eventClient.startConnection();
                                eventClient.sendData(request.toString());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        this.classes.getChildren().add(button);
                        loadedTeacherClasses.put((String) classData.get("name"), (String) classData.get("uuid"));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }));

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("request_type", "get-user-classes");
            jsonObject.put("username", LoginFormController.loggedInUsername);
            client.startConnection();
            client.sendData(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
