package app.cleancode.graal_test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String [] args) {
		launch (args);
	}
private Screen screen; 

	@Override
	public void start(Stage primaryStage) throws Exception {
		screen = Screen.getPrimary();
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
		scene.setRoot(buildGame());
	}
	public Group buildGame () {
		Group result = new Group ();
		Image background = new Image("https://c.files.bbci.co.uk/12A9B/production/_111434467_gettyimages-1143489763.jpg", screen.getBounds().getWidth(), screen.getBounds().getHeight(), false, false);
		ImageView backgroundView = new ImageView(background);
		result.getChildren().add(backgroundView);
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
		rectangle.setOnTouchReleased(evt -> {
			toggleColors (rectangle);
		});
		rectangle.setOnMouseReleased(evt -> {
			toggleColors(rectangle);
		});
		Text infoText;
		try {
			infoText = new Text(Files.list(Paths.get(".")).map(Path::toAbsolutePath).map(Path::toString).collect(Collectors.toList()).toString());
		} catch (IOException e) {
			infoText = new Text(e.toString());
		}
		result.getChildren().add(infoText);
		return result;
	}
	public void toggleColors (Shape s) {
		if (s.getFill().equals(Color.ORANGE)) {
			s.setFill(Color.GREEN);
		}else {
			s.setFill(Color.ORANGE);
		}
	}
		}
