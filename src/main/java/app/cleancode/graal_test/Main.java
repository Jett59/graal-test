package app.cleancode.graal_test;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String [] args) {
		launch (args);
	}
 
	@Override
	public void start(Stage primaryStage) throws Exception {
		Screen screen = Screen.getPrimary();
		Rectangle rectangle = new Rectangle (100, 100);
		rectangle.setX(screen.getBounds().getWidth() / 2d - rectangle.getWidth() / 2d);
		rectangle.setY(screen.getBounds().getHeight() / 2d - rectangle.getHeight() / 2d);
		Group root = new Group(rectangle);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Yay!");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.show();
		RotateTransition rotation = new RotateTransition(Duration.seconds(1), rectangle);
		rotation.setAutoReverse(false);
		rotation.setByAngle(360);
		rotation.setCycleCount(-1);
		rotation.setInterpolator(Interpolator.LINEAR);
		rotation.play();
		Group gameRoot = buildGame ();
		scene.setRoot(gameRoot);
	}
	public Group buildGame () {
		Group result = new Group ();
		Rectangle rectangle = new Rectangle(200, 200, Color.ORANGE);
		result.getChildren().add(rectangle);
		rectangle.setOnScroll(evt -> {
			rectangle.setX(evt.getSceneX() - rectangle.getWidth() / 2d);
			rectangle.setY(evt.getSceneY() - rectangle.getHeight() / 2d);
		});
		rectangle.setOnMouseDragged(evt -> {
			rectangle.setX(evt.getSceneX() - rectangle.getWidth() / 2d);
			rectangle.setY(evt.getSceneY() - rectangle.getHeight() / 2d);
		});
		return result;
	}
		}
