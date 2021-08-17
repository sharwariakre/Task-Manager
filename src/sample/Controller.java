package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

	@FXML private TextField NewTask;
	@FXML private ListView<String> TaskList;
	private String filePath = "C:\\Users\\Admin\\Desktop\\SE\\JAVA_1\\TaskManager\\data.txt";
	private File data = new File(filePath);

	public void AddNewTask(ActionEvent e){
		String text = NewTask.getText();
		if (!text.equals("")){
			TaskList.getItems().add(text);
			NewTask.setText((""));
		} else {
			System.out.println("No input...");
		}
	}

	public void DeleteTask (ActionEvent e) {
		String selected = TaskList.getSelectionModel().getSelectedItem();
		if (selected != null) {
			TaskList.getItems().remove(selected);
		} else {
			System.out.println("No Task Selected");
		}
	}

	public void ExitProgram(ActionEvent e){

		List<String> currentTasks = TaskList.getItems();

		try {
			FileWriter writer = new FileWriter(filePath);
			for (String text: currentTasks) {
				text += "\n";
				writer.write(text);
			}
			writer.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Scanner myReader = new Scanner(data);

			while (myReader.hasNextLine()) {
				String text = myReader.nextLine();
				TaskList.getItems().add(text);

			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
