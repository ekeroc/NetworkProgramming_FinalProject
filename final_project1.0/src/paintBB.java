import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class paintBB {
	
	Canvas canvas = new Canvas(800,500);
	GraphicsContext gc;
	Slider slider = new Slider();
	Label label = new Label("1.0");
	GridPane grid = new GridPane();
	ColorPicker cp = new ColorPicker();
	StackPane pane = new StackPane();
	Scene scene = new Scene(pane,800,500);
	
	public void Start() {
		Stage primaryStage = new Stage();
		try {
			gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(10);
			
			cp.setValue(Color.BLACK);
			cp.setOnAction(e->{
				gc.setStroke(cp.getValue());
			});
			
			slider.setMin(1);
			slider.setMax(100);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.valueProperty().addListener(e->{
				double value = slider.getValue();
				String str = String.format("%.1f", value);
				label.setText(str);
				gc.setLineWidth(value);
			});
			
			
			grid.addRow(0, cp, slider,label);
			grid.setHgap(20);
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setPadding(new Insets(20,0,0,0));
			
			scene.setOnMousePressed(e ->{
				gc.beginPath();
				gc.lineTo(e.getSceneX(),e.getSceneY());
				gc.stroke();
			});
			 
			scene.setOnMouseDragged(e ->{
				gc.lineTo(e.getSceneX(),e.getSceneY());
				gc.stroke();
			});
			
			pane.getChildren().addAll(canvas, grid);
			
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Paint board with JavaFX");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
