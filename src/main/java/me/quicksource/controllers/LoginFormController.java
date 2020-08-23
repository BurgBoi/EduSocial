package me.quicksource.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import me.quicksource.Client;
import me.quicksource.GuiUtils;
import me.quicksource.QuickSourceApplication;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

	public static String loggedInUsername;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label invalidUsernameLabel;

	@FXML
	private Label wrongPasswordLabel;

	public void onMouseClickedLogin(MouseEvent mouseEvent) throws Exception {
		Client client = new Client("127.0.0.1", 5000, data -> {
			try {
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(data.toString());
				if (jsonObject.get("dataType").equals("login-callback")) {
					if (jsonObject.containsKey("error")) {
						if (jsonObject.get("error").equals("invalid-username")) {
							invalidUsernameLabel.setOpacity(1);
							return;
						} else if (jsonObject.get("error").equals("invalid-password")) {
							wrongPasswordLabel.setOpacity(1);
							return;
						}
					} else if (jsonObject.containsKey("account_type")) {
						loggedInUsername = username.getText();
						if (jsonObject.get("account_type").equals("teacher")) {
							GuiUtils.changeScene(QuickSourceApplication.stage, "teacherMenu.fxml");
						} else if (jsonObject.get("account_type").equals("student")) {
							GuiUtils.changeScene(QuickSourceApplication.stage, "studentMenu.fxml");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("request_type", "login");
		jsonObject.put("username", username.getText());
		jsonObject.put("password", password.getText());
		client.startConnection();
		client.sendData(jsonObject.toString());
	}

	public void onMouseClickedRegister(MouseEvent mouseEvent) throws Exception {
		Parent registerformRoot = FXMLLoader.load(getClass().getClassLoader().getResource("registerform.fxml"));
		QuickSourceApplication.stage.setScene(new Scene(registerformRoot));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}

