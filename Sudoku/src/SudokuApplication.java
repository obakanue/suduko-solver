import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SudokuApplication extends Application {
	private OnlyNumbersTextField[][] field;
	private SudokuSolver solver = new SudokuSolver();
	private boolean solved;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		BorderPane root = new BorderPane();
		TilePane gridPanel = new TilePane();
		HBox commandPanel = new HBox();
		Button solveButton = new Button("Solve");
		Button clearButton = new Button("Clear");
		field = new OnlyNumbersTextField[9][9];
		Scene scene = new Scene(root, 470, 520);
		root.setStyle("-fx-background-color: #303030;");

		designGridPanel(field, gridPanel);
		designCommandPanel(commandPanel, solveButton, clearButton);
		designScene(scene, stage);

		solveButton.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 18;" + "-fx-font-weight: bold;"
				+ "-fx-base: #303030;");
		clearButton.setStyle("-fx-font-family: 'Courier New';" + "-fx-font-size: 18;" + "-fx-font-weight: bold;"
				+ "-fx-base: #303030;");

		root.setCenter(gridPanel);
		root.setBottom(commandPanel);

		solveButton.setOnAction(event -> {
			pressedSolveButton();
		});

		clearButton.setOnAction(event -> {
			pressedClearButton();
		});
	}

	private void designGridPanel(OnlyNumbersTextField[][] field, TilePane gridPanel) {
		gridPanel.setPrefColumns(9);
		gridPanel.setPrefRows(9);
		gridPanel.setVgap(2);
		gridPanel.setHgap(2);
		gridPanel.setAlignment(Pos.CENTER);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				field[i][j] = new OnlyNumbersTextField("");
				field[i][j].setPrefSize(50, 50);
				field[i][j].setAlignment(Pos.CENTER);
				gridPanel.getChildren().add(field[i][j]);
				if (i / 3 != 1 && j / 3 != 1 || i / 3 == 1 && j / 3 == 1) {
					field[i][j].setStyle("-fx-background-color: #b4b4b4;" + "-fx-font-family: 'Courier New';"
							+ "-fx-font-size: 24;" + "-fx-font-weight: bold;");
				} else {
					field[i][j].setStyle(
							"-fx-font-family: 'Courier New';" + "-fx-font-size: 24;" + "-fx-font-weight: bold;");
				}
			}
		}
	}

	private void designCommandPanel(HBox commandPanel, Button solveButton, Button clearButton) {
		commandPanel.setPrefSize(450, 50);
		commandPanel.getChildren().addAll(solveButton, clearButton);
		commandPanel.setAlignment(Pos.CENTER);
		commandPanel.setSpacing(20);
		commandPanel.setStyle("-fx-background-color: #303030;");
	}

	private void designScene(Scene scene, Stage stage) {
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.setTitle("Gottis and Obakas Sudoku Solver");
		stage.show();
	}

	private void designAlert(Alert alert) {
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setResizable(false);
	}

	private void pressedSolveButton() {
		solver.checkInput(field);
		Alert alert = new Alert(AlertType.INFORMATION);
		designAlert(alert);
		if (solver.solve(0, 0)) {
			int[][] temp = solver.getSolvedSudoku();
			solved = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					field[i][j].setText(Integer.toString(temp[i][j]));
				}
			}
		} else {
			solved = false;
		}
		if (solved) {
			alert.setContentText("A solution was found!");
			solver.resetIllegalInput();
			alert.showAndWait();
		} else {
			if (solver.getIllegalInputState()) {
				alert.setContentText(
						"Illegal sudoku, you have to put unique numbers for every row, column and 3x3 area! Please adjust the numbers and try again.");
				solver.resetIllegalInput();
				alert.showAndWait();
			} else {
				alert.setContentText(
						"No solution could be found. Try another puzzle or feel free to adjust the current one!");
				solver.resetIllegalInput();
				alert.showAndWait();

			}
		}
	}

	private void pressedClearButton() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				field[i][j].setText("");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
