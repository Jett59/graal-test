package app.cleancode.graal_test;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String [] args) {
		launch (args);
	}
 
	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle rectangle = new Rectangle(100, 100);
		Group root = new Group(rectangle);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Yay!");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.show();
		RotateTransition rotation = new RotateTransition(Duration.seconds(1), rectangle);
		rotation.setAutoReverse(true);
		rotation.setCycleCount(-1);
		rotation.setByAngle(360);
		rotation.play();
	}
		}
