import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacTT {

	private static final Cell	Cell			= null;
	private char				currentPlayer	= 'X';
	public Cell[][]				cell			= new Cell[3][3];
	private Label				statusMsg		= new Label("X must play");
	// TODO Auto-generated method stub

	Stage						primaryStage	= new Stage();
	GridPane					pane			= new GridPane();
	Main						main;
	Client						c;
	String						nameClient;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	public void setName(String name) {
		this.nameClient = name;
	}

	public TicTacTT.Cell getCell() {
		return TicTacTT.Cell;
	}

	public void Start() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cell[i][j] = new Cell();
				pane.add(cell[i][j], j, i);
			}
		}

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(statusMsg);

		Scene scene = new Scene(borderPane, 450, 450);
		primaryStage.setTitle("Tic Tac Toe with JavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j].getPlayer() == ' ') {
					return false;
				}
			}
		}

		return true;
	}

	public boolean hasWon(char player) {
		for (int i = 0; i < 3; i++) {
			if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player
					&& cell[i][2].getPlayer() == player) {
				return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player
					&& cell[2][i].getPlayer() == player) {
				return true;
			}
		}
		if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
			return true;
		}
		if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
			return true;
		}

		return false;
	}

	public class Cell extends Pane {
		private char player = ' ';

		public Cell() {
			setStyle("-fx-border-color :  red");
			this.setPrefSize(300, 300);
			this.setOnMouseClicked(e -> {
				handleClick();

			});
		}

		private void handleClick() {
			// TODO Auto-generated method stub
			if (player == ' ' && currentPlayer != ' ') {
				setPlayer(currentPlayer);

				if (hasWon(currentPlayer)) {
					statusMsg.setText(currentPlayer + " Won !");
					currentPlayer = ' ';
				} else if (isBoardFull()) {
					statusMsg.setText(" Draw !");
					currentPlayer = ' ';
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
					statusMsg.setText(currentPlayer + " must play");
				}
			}
		}

		public char getPlayer() {
			return player;
		}

		public void setPlayer(char c) {
			player = c;

			if (player == 'X') {
				Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));

				Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
				line2.endXProperty().bind(this.widthProperty().subtract(10));
				line2.startYProperty().bind(this.heightProperty().subtract(10));

				getChildren().addAll(line1, line2);

			} else if (player == 'O') {

				Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10,
						this.getHeight() / 2 - 10);

				ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				ellipse.setStroke(Color.BLACK);
				ellipse.setFill(Color.BLUE);

				getChildren().add(ellipse);

			}

		}

	}
}
