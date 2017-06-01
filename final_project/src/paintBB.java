import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.javafx.binding.DoubleConstant;

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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sun.security.ec.ECDHKeyAgreement;

public class paintBB {

	Canvas			canvas	= new Canvas(800, 500);
	GraphicsContext	gc;
	Slider			slider	= new Slider();
	Label			label	= new Label("1.0");
	GridPane		grid	= new GridPane();
	ColorPicker		cp		= new ColorPicker();
	StackPane		pane	= new StackPane();
	Scene			scene	= new Scene(pane, 800, 500);

	Main			main;
	Client			c;
	String			nameClient;
	double			value;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	public void setName(String name) {
		this.nameClient = name;
	}

	public void Start() {
		c.setPaintBB(this);

		Stage primaryStage = new Stage();
		try {
			gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(10);

			cp.setValue(Color.BLACK);
			cp.setOnAction(e -> {
				gc.setStroke(cp.getValue());

			});

			slider.setMin(1);
			slider.setMax(100);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.valueProperty().addListener(e -> {
				value = slider.getValue();
				String str = String.format("%.1f", value);
				label.setText(str);
				gc.setLineWidth(value);

				try {
					if (c.getName() != nameClient) {
						main.setPaintLabel(nameClient, value);
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});

			grid.addRow(0, cp, slider, label);
			grid.setHgap(20);
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setPadding(new Insets(20, 0, 0, 0));

			scene.setOnMousePressed(e -> {
				gc.beginPath();
				gc.lineTo(e.getSceneX(), e.getSceneY());
				gc.stroke();

				double x = e.getSceneX(), y = e.getSceneY();
				try {
					if (c.getName() != nameClient) {
						main.paintToClient(nameClient, x, y);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});

			scene.setOnMouseDragged(e -> {

				gc.lineTo(e.getSceneX(), e.getSceneY());
				gc.stroke();

				double x = e.getSceneX(), y = e.getSceneY();

				try {

					if (c.getName() != nameClient) {
						main.paintToClient(nameClient, x, y);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			pane.getChildren().addAll(canvas, grid);

			//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Paint board with JavaFX");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGc(double x, double y) {

		gc.lineTo(x, y);
		gc.stroke();
	}

	public void setLabel(double value) {
		gc.setLineWidth(value);

	}
}
