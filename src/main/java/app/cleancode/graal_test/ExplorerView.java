package app.cleancode.graal_test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class ExplorerView {
	private StringProperty path;

public void start (Pane root, Screen screen) {
	try {
		path = new SimpleStringProperty();
		path.set(System.getProperty("user.home"));
		BorderPane wholeScreen = new BorderPane();
		FlowPane files = new FlowPane();
		TextField pathText = new TextField();
		pathText.setEditable(false);
		pathText.textProperty().bind(path);
		pathText.setFont(new Font(48));
		wholeScreen.setTop(pathText);
		BorderPane.setAlignment(pathText, Pos.CENTER);
		wholeScreen.setCenter(new ScrollPane(files));
		root.setStyle("-fx-background-color: white;");
		root.getChildren().add(wholeScreen);
		populateFiles (files);
	}catch (Exception e) {
		Text msg = new Text (e.toString());
		msg.setTranslateX((screen.getBounds().getWidth() + msg.getBoundsInLocal().getWidth()) / 2d);
		msg.setTranslateY((screen.getBounds().getHeight() + msg.getBoundsInLocal().getHeight()) / 2d);
		root.getChildren().add(msg);
	}
}
public void populateFiles (FlowPane root) throws Exception {
	root.getChildren().clear();
	Stream<Path> files = Files.list(Paths.get(path.get()));
	files.map(Path::getFileName).map(Path::toString).forEach(file -> {
		Button fileButton = new Button(file);
		fileButton.setOnAction(evt -> {
			if (new File(Paths.get(path.get(), file).toAbsolutePath().toString()).isDirectory()) {
			path.set(Paths.get(path.get(), file).toAbsolutePath().toString());
			try {
				populateFiles(root);
			} catch (Exception e) {
				
			}
			}
		});
		fileButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
		root.getChildren().add(fileButton);
	});
}
}
