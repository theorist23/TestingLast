import java.time.ZoneId;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UI extends Application{

	public static void main(String[] args) throws Exception {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Hospital hospital = new Hospital();
		hospital.initializeHospital();
		for (int i = 0; i < hospital.getDoctors().size(); i++) {
			try {
				hospital.checkInDoctor(hospital.getDoctors().get(i));
			} catch (Exception e) {
				System.out.print(e.getMessage() + " - ");
				System.out.println("Couldn't check in doctor " + hospital.getDoctors().get(i).getFirstName() + " " + hospital.getDoctors().get(i).getLastName());
			}
		}
		
		
		
		// Adding a patient
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
		Label patientFirstName = new Label("First name");
		grid.add(patientFirstName, 0, 0);
		Label patientLastName = new Label("Last name");
		grid.add(patientLastName, 0, 1);
		Label patientBirthdate = new Label("Brithdate");
		grid.add(patientBirthdate, 0, 2);
		Label patientSpecialtyNeeded = new Label("Specialty needed");
		grid.add(patientSpecialtyNeeded, 0, 3);
		
		
		TextField patientFirstNameTextField = new TextField();
		grid.add(patientFirstNameTextField, 1, 0);
		TextField patientLastNameTextField = new TextField();
		grid.add(patientLastNameTextField, 1, 1);	
		DatePicker patientBrithdatePicker = new DatePicker();
		grid.add(patientBrithdatePicker, 1, 2);
		ComboBox<Specialty> patientSpecialtyNeededPicker = new ComboBox<>();
		patientSpecialtyNeededPicker.getItems().setAll(FXCollections.observableArrayList(Specialty.values()));
		grid.add(patientSpecialtyNeededPicker, 1, 3);
		
		
		Button enterButton = new Button("Enter");
		grid.add(enterButton, 1, 4);
		
		enterButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Patient patient = new Patient(patientFirstNameTextField.getText(), patientLastNameTextField.getText(),
						GregorianCalendar.from(patientBrithdatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault())), 
						patientSpecialtyNeededPicker.getValue());
				try {
					System.out.println(patient.getSpecialtyNeeded());
					hospital.checkInPatient(patient);
					hospital.updateWaitingLists();
					for (int i = 0; i < hospital.getExaminationRooms().size(); i++) {
						ExaminationRoom room = hospital.getExaminationRooms().get(i);
						try {
						System.out.println(room.getOccupyingDoctor().getFirstName());
						System.out.println(room.getOccupyingPatient().getFirstName());
						for (int j = 0; j < room.getWaitingPatients().size(); j++) {
							System.out.print(room.getWaitingPatients().get(j).getFirstName() + ", ");
						}
						}
						catch(Exception e){
							System.out.println(e.getMessage());
						}
						System.out.println();
					}
					
				
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}

			}
			
		});
		
		Scene loginScene = new Scene(grid, 300, 275);
        primaryStage.setScene(loginScene);
		primaryStage.show();		
	}

}
