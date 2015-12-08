import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		long b = 12;
		long c = 6;
		long a = 36;
		a = b << 32 | c;
		System.out.println(a+" "+b+" "+c);
		Hospital hospital = new Hospital();
		hospital.initializeHospital();
		
		// Checking in all existing doctors
		for (int i = 0; i < hospital.getDoctors().size(); i++) {
			try {
				hospital.checkInDoctor(hospital.getDoctors().get(i));
			} catch (Exception e) {
				System.out.print(e.getMessage() + " - ");
				System.out.println("Couldn't check in doctor " + hospital.getDoctors().get(i).getFirstName() + " " + hospital.getDoctors().get(i).getLastName());
			}
		}
		Patient patient1 = new Patient("Pearu", "Org", new GregorianCalendar(1954, 4, 30), Specialty.PSY);
		Patient patient2 = new Patient("Andres", "Mägi", new GregorianCalendar(1946, 1, 31), Specialty.PSY);
		Patient patient3 = new Patient("Krõõt", "Mägi", new GregorianCalendar(1946, 1, 31), Specialty.DET);
		Patient patient4 = new Patient("Mari", "Mägi", new GregorianCalendar(1946, 1, 31), Specialty.ENT);
		Patient patient5 = new Patient("Indrek", "Mägi", new GregorianCalendar(1966, 1, 31), Specialty.ENT);
		Patient patient6 = new Patient("Karin", "Mägi", new GregorianCalendar(2003, 1, 31), Specialty.SUR);
		
		hospital.checkInPatient(patient1);
		hospital.checkInPatient(patient2);
		hospital.checkInPatient(patient3);
		hospital.checkInPatient(patient4);
		hospital.checkInPatient(patient5);
		hospital.checkInPatient(patient6);
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
		
	}
}
