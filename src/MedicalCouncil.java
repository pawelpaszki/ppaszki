import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is a main controller. It contains collection of all doctors, which
 * is instantiated during creation object of this class. doctors can be searched
 * added, removed. This class also contains methods, which allow to display info
 * about registration fees. Scanner object is also created during construction,
 * which will later be used to get user input
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class MedicalCouncil {

	private ArrayList<Doctor> doctors;
	private Scanner input;
	private int nextAvailableComplaintId;

	public static void main(String[] args) throws Exception {
		MedicalCouncil council = new MedicalCouncil();
		council.runMenu();
	}

	/**
	 * Constructor for objects of class MedicalCouncil new ArrayList of doctors
	 * is instantiated
	 */
	private MedicalCouncil() {
		doctors = new ArrayList<Doctor>();
		input = new Scanner(System.in);
		nextAvailableComplaintId = 0;
	}

	/**
	 * @return nextAvailableComplaintId, which will be assigned each time new
	 *         complaint is created
	 */
	private int getNextAvailableComplaintId() {
		return nextAvailableComplaintId;
	}

	/**
	 * This is the driver of the MedicalCouncil. It display 15 options to manage
	 * the system and
	 * 
	 * @return int value representing picked option from the menu
	 */
	private int mainMenu() {
		int option = 0;
		boolean correct = false;
		do {
			try {
				System.out.println("  Medical Council Menu");
				System.out.println("  ---------");
				System.out.println("  1) Add a Specialist Doctor");
				System.out.println("  2) Add a General Doctor");
				System.out.println("  3) Add an Intern");
				System.out.println("  4) Delete a Doctor");
				System.out.println("  5) Edit Doctor's Details");
				System.out
						.println("  6) Display Total Amount of Registration Owed To The Council");
				System.out
						.println("  7) Display Total Amount of Registration Owed By Interns");
				System.out
						.println("  8) Display Total Amount of Registration Owed By General Doctors");
				System.out
						.println("  9) Display Total Amount of Registration Owed By Specialists");
				System.out
						.println("  10) Display Average Amount of Registration Owed To The Council");
				System.out.println("  11) List All Doctors");
				System.out.println("  12) Search for Doctor");
				System.out.println("  13) Display Full Info about a Doctor");
				System.out.println("  14) Complaints Management");
				System.out.println("  15) Display Full Details of all Doctors");
				System.out.println("  0) Exit");
				System.out.print("==>> ");
				option = input.nextInt();
				correct = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("\nNumber needed... Please try again\n");
			}
		} while (!correct);
		return option;
	}

	/**
	 * This is the method that controls the loop.
	 */
	private void runMenu() {
		int option = mainMenu();

		while (option != 0) {

			switch (option) {
			case 1:
			case 2:
			case 3:
				enterNewDoctorDetails(option);
				break;
			case 4:
				if (!noDoctors()) {
					System.out
							.println("Please enter the name of the doctor to delete");
					String name = input.nextLine();
					name = input.nextLine();
					if (deleteDoctor(name) != null) {
						System.out.println(deleteDoctor(name).getName()
								+ " has been removed from the council");
					} else {
						System.out
								.println("Doctor with this name has not been found");
					}
				} else {
					System.out.println("There are no doctors in the Council");
				}
				break;
			case 5:
				if (!noDoctors()) {
					editDoctorsDetails();
				} else {
					System.out.println("There are no doctors in the Council");
				}
				break;
			case 6:
				System.out
						.println("Total Amount of Registration Owed To The Council is: "
								+ totalRegistrationOwed());
				break;
			case 7:
				System.out
						.println("Total Amount of Registration Owed By Interns is: "
								+ totalInternRegistrationOwed());
				break;
			case 8:
				System.out
						.println("Total Amount of Registration Owed By General Doctors is: "
								+ totalGeneralRegistrationOwed());
				break;
			case 9:
				System.out
						.println("Total Amount of Registration Owed By Specialist Doctors is: "
								+ totalSpecialistRegistrationOwed());
				break;
			case 10:
				double internsAverage;
				if (noInterns()) {
					internsAverage = 0;
				} else {
					internsAverage = 310;
				}
				System.out
						.println("Average value of Registration owed by all Types of Doctors: "
								+ "\nInterns: "
								+ internsAverage
								+ "\nGeneral: "
								+ averageRegistrationOwed()[0]
								+ "\nSpecialist: "
								+ averageRegistrationOwed()[1]);
				break;
			case 11:
				System.out.println(allDoctors());
				break;
			case 12:
				if (!noDoctors()) {
					System.out
							.println("Please enter the name of the doctor to search for");
					String doctorName = input.nextLine();
					doctorName = input.nextLine();
					if (searchForDoctor(doctorName) != -1) {
						System.out.println("The index of " + doctorName
								+ " is: " + searchForDoctor(doctorName));
					} else {
						System.out.println("Doctor with following name: "
								+ doctorName + " has not been found");
					}
				} else {
					System.out.println("There are no doctors in the Council");
				}

				break;
			case 13:
				System.out.println(fullDetails());
				break;
			case 14:
				if (!noDoctors() && !noQualifiedDoctors()) {
					manageComplaints();
				} else {
					System.out
							.println("There are no (qualified) doctors in the Council");
				}
				break;
			case 15:
				System.out.println(toString());
				break;
			default:
				System.out.println("Invalid option entered: " + option);
				break;
			}

			// pause the program so that the user can read what we just printed
			// to the terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			input.nextLine(); // this second read is required - bug in
			// Scanner
			// class; a String read is ignored straight
			// after reading an int.

			// display the main menu again
			option = mainMenu();
		}

		// the user chose option 0, so exit the program
		System.out.println("Exiting... bye");
		System.exit(0);
	}

	/**
	 * 
	 * @param type
	 *            is passed to determine what type of doctor will be added to
	 *            the council: 1 - specialist 2 - general 3 - intern and all
	 *            required information is collected using Scanner and then
	 *            passed as a new object to addDoctor method
	 */
	private void enterNewDoctorDetails(int type) {
		System.out.print("Please enter doctor's full name (Surname first): ");
		String name = input.nextLine();
		name = input.nextLine();
		System.out.print("Please enter doctor's year of birth: ");
		int year = 0;
		boolean correct = false;
		do {
			try {
				year = input.nextInt();
				correct = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		System.out.print("Please enter doctor's month of birth: ");
		int month = 0;
		correct = false;
		do {
			try {
				month = input.nextInt();
				correct = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		System.out.print("Please enter doctor's day of birth: ");
		int day = 0;
		correct = false;
		do {
			try {
				day = input.nextInt();
				correct = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		System.out.print("Please enter doctor's gender: ");
		String gender = input.nextLine();
		gender = input.nextLine();
		System.out.print("Please enter doctor's address: ");
		String address = input.nextLine();
		System.out
				.print("Please enter doctor's contact number (without 0 at the start): ");
		int contactNumber = 0;
		correct = false;
		do {
			try {
				contactNumber = input.nextInt();
				correct = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		boolean qualifiedInIreland;
		if (type == 1 || type == 2) {
			System.out.print("Was the doctor qualified in Ireland (y/n): ");
			char currentProduct = input.next().charAt(0);
			qualifiedInIreland = false;
			if ((currentProduct == 'y') || (currentProduct == 'Y'))
				qualifiedInIreland = true;
			if (type == 1) {
				System.out.print("Please enter doctor's specialisation: ");
				String specialist = input.nextLine();
				specialist = input.nextLine();
				addDoctor(new Specialist(name, year, month, day, gender,
						address, contactNumber, qualifiedInIreland, specialist));
			} else {
				addDoctor(new General(name, year, month, day, gender, address,
						contactNumber, qualifiedInIreland));
			}
		} else {
			addDoctor(new Internship(name, year, month, day, gender, address,
					contactNumber));
		}
		boolean done = false;
		System.out.println("Please enter your qualifications one at a time. "
				+ "\nPress any key to start entering..");
		String degreeType;
		String degreeName;
		String collegeName;
		int conferringYear;

		input.nextLine();
		input.nextLine();
		do {
			System.out
					.println("Please enter your Type of Degree (ie Bachelor of Medicine)");
			degreeType = input.nextLine();
			System.out.println("Please enter your Degree Name");
			degreeName = input.nextLine();
			System.out.println("Please enter your College Name");
			collegeName = input.next();
			input.nextLine();
			System.out.println("Please enter Conferring Year");
			conferringYear = 0;
			correct = false;
			do {
				try {
					conferringYear = input.nextInt();
					/*
					 * if ((conferringYear - 20) < year) { conferringYear =
					 * 1935; }
					 */
					correct = true;
				} catch (InputMismatchException e) {
					input.nextLine();
					System.out.println("Number needed... Please try again");
				}
			} while (!correct);
			System.out.println("Please type done, when finished "
					+ "\n or any key to continue entering qualifications");
			input.nextLine();
			if (input.nextLine().equalsIgnoreCase("done")) {
				done = true;
			}
			doctors.get(doctors.size() - 1).qualifications
					.add(new Qualification(degreeType, degreeName, collegeName,
							conferringYear));
		} while (!done);

	}

	/**
	 * 
	 * @param doctor
	 *            is added to Collection of doctors
	 */
	private void addDoctor(Doctor doctor) {
		doctors.add(doctor);
	}

	/**
	 * 
	 * @param name
	 *            is passed and if a doctor with specified name exists - he/she
	 *            is deleted from doctors collection, assuming that there is
	 *            only one doctor with this name, if there is more than one
	 *            doctor with the passed name, than details of doctors with this
	 *            name will be displayed along with an option - which one to
	 *            delete
	 * @return deleted doctor or null, if doctor does not exist
	 */
	private Doctor deleteDoctor(String name) {
		int counter = 0;
		int doctorIndex = -1;
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getName().equalsIgnoreCase(name)) {
				indices.add(i);
				counter++;
				doctorIndex = i;
			}
		}
		if (counter == 1) {
			Doctor doctor = doctors.get(doctorIndex);
			doctors.remove(doctorIndex);
			return doctor;
		} else if (counter > 1) {
			System.out
					.println("There is more than one doctor with the same name.\n"
							+ "Please choose index of doctor to be deleted\n");
			for (int i = 0; i < indices.size(); i++) {
				System.out.println("Index " + indices.get(i) + ":\n"
						+ doctors.get(indices.get(i)).toString() + "\n");
			}
			doctorIndex = 0;
			boolean correct = false;
			do {
				try {
					doctorIndex = input.nextInt();
					if (indices.contains(doctorIndex)) {
						correct = true;
					} else {
						System.out.println("Invalid index. Please try again");
					}
				} catch (InputMismatchException e) {
					input.nextLine();
					System.out.println("Number needed... Please try again");
				}
			} while (!correct);
			Doctor doctor = doctors.remove(doctorIndex);
			return doctor;

		} else {
			return null;
		}
	}

	/**
	 * this method allows to edit details (1 of 3 options - name, address or
	 * contact number) of a specified doctor
	 */
	private void editDoctorsDetails() {
		System.out.println("Doctors in the council: ");
		for (int i = 0; i < doctors.size(); i++) {
			System.out.println(i + " :" + doctors.get(i).getName());
		}
		System.out
				.print("\nPlease select an index of doctor to change his/ her details: ");
		boolean correct = false;
		input.nextLine();
		int doctorIndex = 0;
		do {
			try {
				doctorIndex = input.nextInt();
				if (doctorIndex >= 0 && doctorIndex < doctors.size()) {
					correct = true;
				} else {
					System.out
							.println("Invalid number entered. Please enter correct index >> ");
				}
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		System.out.println("  Please select one of the following options: ");
		System.out.println("  ---------");
		System.out.println("  1) Edit Name");
		System.out.println("  2) Edit Address");
		System.out.println("  3) Edit Contact Number");
		System.out.println("==>>  ");
		correct = false;
		int editOption = 0;
		do {
			try {
				editOption = input.nextInt();
				if (editOption >= 1 && editOption <= 3) {
					correct = true;
				} else {
					System.out
							.println("Invalid option selected. Please select 1,2 or 3");
				}
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		switch (editOption) {
		case 1:
			System.out.println("Please enter new name: ");
			input.nextLine();
			String name = input.nextLine();
			doctors.get(doctorIndex).setName(name);
			break;
		case 2:
			System.out.println("Please enter new address: ");
			input.nextLine();
			String address = input.nextLine();
			doctors.get(doctorIndex).setAddress(address);
			break;
		case 3:
			System.out.println("Please enter new contact number: ");
			input.nextLine();
			int contactNumber = 0;
			do {
				try {
					editOption = input.nextInt();
					correct = true;
				} catch (InputMismatchException e) {
					input.nextLine();
					System.out.println("Number needed... Please try again");
				}
			} while (!correct);
			doctors.get(doctorIndex).setContactNumber(contactNumber);
			break;
		default:
			System.out.println("Invalid option. Press any key to continue...");
			break;

		}

	}

	/**
	 * @param name
	 *            of a doctor is passed and
	 * @return index of a doctor, if in the council, otherwise -1
	 */
	private int searchForDoctor(String name) {
		int index = -1;
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).name.equalsIgnoreCase(name)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * @return total value of registration owed to the council
	 */
	private double totalRegistrationOwed() {
		double totalRegistration = 0;
		for (Doctor doctor : doctors) {
			totalRegistration += doctor.calculateRegistrationFee();
		}
		return totalRegistration;
	}

	/**
	 * @return total value of registration owed to the council by Interns
	 */
	private double totalInternRegistrationOwed() {
		double totalInternRegistration = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof Internship) {
				totalInternRegistration += doctor.calculateRegistrationFee();
			}
		}
		return totalInternRegistration;
	}

	/**
	 * @return total value of registration owed to the council by General
	 *         doctors
	 */
	private double totalGeneralRegistrationOwed() {
		double totalGeneralRegistration = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof General) {
				totalGeneralRegistration += doctor.calculateRegistrationFee();
			}
		}
		return totalGeneralRegistration;
	}

	/**
	 * @return total value of registration owed to the council by Specialists
	 */
	private double totalSpecialistRegistrationOwed() {
		double totalSpecialistRegistration = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof Specialist) {
				totalSpecialistRegistration += doctor
						.calculateRegistrationFee();
			}
		}
		return totalSpecialistRegistration;
	}

	/**
	 * @return average value of registration owed to the council
	 */
	private double[] averageRegistrationOwed() {
		int generals = 0;
		int specialists = 0;
		double generalAverage = 0;
		double specialistAverage = 0;
		double[] averageValues = new double[2];
		for (Doctor doctor : doctors) {
			if (doctor instanceof General) {
				generals++;
			} else if (doctor instanceof Specialist) {
				specialists++;
			}
		}
		if (generals > 0) {
			generalAverage = toTwoDecimalPlaces(totalGeneralRegistrationOwed()
					/ generals);
			averageValues[0] = generalAverage;
		}
		if (specialists > 0) {
			specialistAverage = toTwoDecimalPlaces(totalSpecialistRegistrationOwed()
					/ specialists);
			averageValues[1] = specialistAverage;
		}

		return averageValues;// toTwoDecimalPlaces(totalRegistration /
								// doctors.size());

	}

	/**
	 * @return the argument but now to two decimal places.
	 */
	private double toTwoDecimalPlaces(double num) {
		return (int) (num * 100) / 100.0;
	}

	/**
	 * @return String containing names of all doctors in ascending order or
	 *         "There are no doctors in the Council" String, if no doctors
	 */
	public String allDoctors() {
		if (doctors.size() == 0) {
			return "There are no doctors in the Council";
		} else {
			String doctorsNames = "\n";
			sortDoctors();
			doctorsNames += "Doctors in the council:\n";
			for (Doctor doctor : doctors) {
				doctorsNames += "\t" + doctor.getName() + "\n";
			}
			return doctorsNames;
		}

	}

	/**
	 * 
	 * @return ArrayList of doctors sorted in ascending order by doctors' names
	 */
	private ArrayList<Doctor> sortDoctors() {
		for (int i = doctors.size() - 1; i >= 0; i--) {
			int highestIndex = i;
			for (int j = i; j >= 0; j--) {
				if (doctors.get(j).getName()
						.compareTo(doctors.get(highestIndex).getName()) > 0) {
					highestIndex = j;
				}
			}
			swapDoctors(doctors, i, highestIndex);
		}
		return doctors;
	}

	/**
	 * 
	 * @param doctors
	 * @param i
	 * @param j
	 *            are passed to swap two elements of doctors ArrayList in order
	 *            to sort the ArrayList in ascending order by doctors' names
	 */
	private void swapDoctors(ArrayList<Doctor> doctors, int i, int j) {
		Doctor lowIndex = doctors.get(i);
		Doctor highIndex = doctors.get(j);
		doctors.set(i, highIndex);
		doctors.set(j, lowIndex);
	}

	/**
	 * if ArrayList of doctors is empty - method will
	 * 
	 * @return "No doctors in the council" String otherwise it will return full
	 *         details of the doctor (by calling its toString())
	 */
	private String fullDetails() {
		if (noDoctors()) {
			return "No doctors in the council";
		} else {
			System.out.println("Doctors in the council: ");
			for (int i = 0; i < doctors.size(); i++) {
				System.out.println(i + " :" + doctors.get(i).getName());
			}
			System.out
					.print("\nPlease select an index of doctor to display info about the doctor: ");
			boolean correct = false;
			input.nextLine();
			int doctorIndex = 0;
			do {
				try {
					doctorIndex = input.nextInt();
					if (doctorIndex >= 0 && doctorIndex < doctors.size()) {
						correct = true;
					} else {
						System.out
								.println("Invalid index chosen. Please enter correct index >> ");
					}
				} catch (InputMismatchException e) {
					input.nextLine();
					System.out.println("Number needed... Please try again");
				}
			} while (!correct);
			return doctors.get(doctorIndex).toString() + "\n";
		}
	}

	/**
	 * this method manages adding, updating, displaying and removing complaints.
	 * firstly user is asked to enter either of 4 options
	 */
	private void manageComplaints() {
		System.out.println("  Please select one of the following options: ");
		System.out.println("  ---------");
		System.out.println("  1) Add Complaint");
		System.out.println("  2) Update Complaint");
		System.out.println("  3) Display Complaints by Doctor");
		System.out.println("  4) Remove Complaint");
		System.out.println("==>>  ");
		boolean correct = false;
		int option = 0;
		do {
			try {
				option = input.nextInt();
				if (option >= 1 && option <= 4) {
					correct = true;
				} else {
					System.out
							.println("Invalid option selected. Please select 1,2,3 or 4");
				}
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Number needed... Please try again");
			}
		} while (!correct);
		ArrayList<Integer> validIndices = new ArrayList<Integer>();
		int doctorIndex = 0;
		switch (option) {
		/*
		 * ArrayList of doctors is iterated and valid indices (those of
		 * qualified doctors) are added to ArrayList of Integers. then user is
		 * prompted to pick on of the indices displayed and if chooses valid one
		 * - a complaint is assigned to the doctor's ArrayList of complaint
		 */
		case 1:
			System.out.println("QuaLified doctors in the council: ");
			for (int i = 0; i < doctors.size(); i++) {
				if (!(doctors.get(i) instanceof Internship)) {
					System.out.println(i + " :" + doctors.get(i).getName());
					validIndices.add(i);
				}
			}
			System.out
					.print("\nPlease select an index of doctor to add new complaint to his/her record: ");
			correct = false;

			do {
				try {
					doctorIndex = input.nextInt();
					if (validIndices.contains(doctorIndex)) {
						correct = true;
					} else {
						System.out
								.println("Invalid index chosen. Please enter correct index >> ");
					}
				} catch (InputMismatchException e) {
					input.nextLine();
					System.out.println("Number needed... Please try again");
				}
			} while (!correct);
			if (doctors.get(doctorIndex) instanceof General) {
				((General) doctors.get(doctorIndex))
						.addComplaint(new Complaint(
								getNextAvailableComplaintId()));
				nextAvailableComplaintId++;
			} else {
				((Specialist) doctors.get(doctorIndex))
						.addComplaint(new Complaint(
								getNextAvailableComplaintId()));
				nextAvailableComplaintId++;
			}
			System.out.println("A complaint has been added to "
					+ doctors.get(doctorIndex).getName() + " record");
			break;
		case 2:
			/*
			 * ArrayList of doctors is iterated and valid indices (those of
			 * qualified doctors) are added to ArrayList of Integers. then user
			 * is prompted to pick on of the indices displayed and if chooses
			 * valid one - list of indices of complains is displayed along with
			 * a prompt to choose one of them. Then user is asked to enter a
			 * status to be changed for the complaint chosen.
			 */
			validIndices = new ArrayList<Integer>();
			doctorIndex = 0;
			if (noComplaints()) {
				System.out.println("No complaints to update");
			} else {
				System.out
						.println("Please select doctor index to update his/her complaints");
				for (int i = 0; i < doctors.size(); i++) {
					if (doctors.get(i) instanceof QualifiedDoctor) {
						if (((QualifiedDoctor) doctors.get(i))
								.getComplaintsCount() > 0) {
							System.out.println(i + ": "
									+ doctors.get(i).getName());
							validIndices.add(i);
						}
					}
				}
				correct = false;
				do {
					try {
						doctorIndex = input.nextInt();
						if (validIndices.contains(doctorIndex)) {
							correct = true;
						} else {
							System.out
									.println("Invalid index chosen. Please enter correct index >> ");
						}
					} catch (InputMismatchException e) {
						input.nextLine();
						System.out.println("Number needed... Please try again");
					}
				} while (!correct);
				validIndices = new ArrayList<Integer>();
				correct = false;
				System.out
						.println("Please select complaint id to update from following valid indices: ");
				for (Complaint complaint : (((QualifiedDoctor) doctors
						.get(doctorIndex)).getComplaints())) {
					System.out.print(complaint.getComplaintId());
					validIndices.add(complaint.getComplaintId());
				}
				int complaintId = 0;
				do {
					try {
						complaintId = input.nextInt();
						if (validIndices.contains(complaintId)) {
							correct = true;
						} else {
							System.out
									.println("Invalid index chosen. Please enter correct index >> ");
						}
					} catch (InputMismatchException e) {
						input.nextLine();
						System.out.println("Number needed... Please try again");
					}
				} while (!correct);
				correct = false;
				System.out
						.println("Please enter status (\"open\",\"upheld\" or \"rejected\"): ");
				input.nextLine();
				String status;
				do {
					status = input.nextLine();
					if (status.equalsIgnoreCase("open")
							|| status.equalsIgnoreCase("rejected")
							|| status.equalsIgnoreCase("upheld")) {
						correct = true;
					} else {
						System.out
								.println("Invalid status entered. Please enter: \"open\",\"upheld\" or \"rejected\"");
					}
				} while (!correct);
				Complaint complaintToUpdate;
				for (int i = 0; i < ((QualifiedDoctor) doctors.get(doctorIndex))
						.getComplaints().size(); i++) {
					if (((QualifiedDoctor) doctors.get(doctorIndex))
							.getComplaints().get(i).getComplaintId() == complaintId) {
						complaintToUpdate = ((QualifiedDoctor) doctors
								.get(doctorIndex)).getComplaints().get(i);
						((QualifiedDoctor) doctors.get(doctorIndex))
								.updateComplaintStatus(complaintToUpdate,
										status);
					}
				}

			}
			break;
		case 3:
			/*
			 * the same mechanism as above. when user successfully picks all the
			 * options complaints of chosen doctor are displayed
			 */
			doctorIndex = 0;
			validIndices = new ArrayList<Integer>();
			correct = false;
			if (noComplaints()) {
				System.out.println("No complaints to display");
			} else {
				System.out
						.println("Please choose an index of a doctor to display his/her complaints details: ");
				for (int i = 0; i < doctors.size(); i++) {
					if (doctors.get(i) instanceof QualifiedDoctor) {
						if (((QualifiedDoctor) doctors.get(i))
								.getComplaintsCount() > 0) {
							System.out.println(i + ": "
									+ doctors.get(i).getName());
							validIndices.add(i);
						}
					}
				}
				do {
					try {
						doctorIndex = input.nextInt();
						if (validIndices.contains(doctorIndex)) {
							correct = true;
						} else {
							System.out
									.println("Invalid index chosen. Please enter correct index >> ");
						}
					} catch (InputMismatchException e) {
						input.nextLine();
						System.out.println("Number needed... Please try again");
					}
				} while (!correct);
				for (Complaint complaint : ((QualifiedDoctor) doctors
						.get(doctorIndex)).getComplaints()) {
					System.out.println(complaint.toString());
				}
			}
			break;
		case 4:
			/*
			 * as above, but this time a complaint gets removed
			 */
			doctorIndex = 0;
			validIndices = new ArrayList<Integer>();
			correct = false;
			if (noComplaints()) {
				System.out.println("No complaints to remove");
			} else {
				System.out
						.println("Please select doctor index to remove his/her complaints");
				for (int i = 0; i < doctors.size(); i++) {
					if (doctors.get(i) instanceof QualifiedDoctor) {
						if (((QualifiedDoctor) doctors.get(i))
								.getComplaintsCount() > 0) {
							System.out.println(i + ": "
									+ doctors.get(i).getName());
							validIndices.add(i);
						}
					}
				}
				do {
					try {
						doctorIndex = input.nextInt();
						if (validIndices.contains(doctorIndex)) {
							correct = true;
						} else {
							System.out
									.println("Invalid index chosen. Please enter correct index >> ");
						}
					} catch (InputMismatchException e) {
						input.nextLine();
						System.out.println("Number needed... Please try again");
					}
				} while (!correct);
				correct = false;
				System.out
						.println("Please select complaint index to remove from following valid indices: ");
				for (Complaint complaint : (((QualifiedDoctor) doctors
						.get(doctorIndex)).getComplaints())) {
					System.out.println(complaint.getComplaintId());
					validIndices.add(complaint.getComplaintId());
				}
				int complaintIndex = 0;
				do {
					try {
						complaintIndex = input.nextInt();
						if (validIndices.contains(complaintIndex)) {
							correct = true;
						} else {
							System.out
									.println("Invalid index chosen. Please enter correct index >> ");
						}
					} catch (InputMismatchException e) {
						input.nextLine();
						System.out.println("Number needed... Please try again");
					}
				} while (!correct);
				System.out.println(((QualifiedDoctor) doctors.get(doctorIndex))
						.getComplaints().remove(complaintIndex).toString()
						+ "has been removed");

			}
			break;
		default:
			System.out.println("Invalid option. Press any key to continue...");
			break;

		}
	}

	/**
	 * 
	 * @return true if there are no doctors or false otherwise
	 */
	private boolean noDoctors() {
		return doctors.size() == 0;
	}

	private boolean noInterns() {
		int count = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof Internship) {
				count++;
			}
		}
		return count == 0;
	}

	/**
	 * 
	 * @return true if there are no qualified doctors or false otherwise
	 */
	private boolean noQualifiedDoctors() {
		int count = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof QualifiedDoctor) {
				count++;
			}
		}
		return count == 0;
	}

	/**
	 * 
	 * @return true if there are no complaints or false otherwise
	 */
	private boolean noComplaints() {
		int count = 0;
		for (Doctor doctor : doctors) {
			if (doctor instanceof QualifiedDoctor) {
				if (((QualifiedDoctor) doctor).getComplaintsCount() > 0) {
					count++;
				}
			}
		}
		return count == 0;
	}

	/**
	 * return String representation of MedicalCouncil, namely full details of
	 * all doctors or String indicating that there are no doctors, if doctors
	 * size is 0
	 */
	public String toString() {
		if (doctors.size() == 0) {
			return "There are no doctors in the council";
		} else {
			String allDoctors = "Doctors in the council: ";
			for (Doctor doctor : doctors) {
				allDoctors += doctor.toString();
			}
			allDoctors += "\nTotal Amount of Registration Owed To The Council is: "
					+ totalRegistrationOwed()
					+ "\nTotal Amount of Registration Owed By Interns is: "
					+ +totalInternRegistrationOwed()
					+ "\nTotal Amount of Registration Owed By General Doctors is: "
					+ totalGeneralRegistrationOwed()
					+ "\nTotal Amount of Registration Owed By Specialist Doctors is: "
					+ totalSpecialistRegistrationOwed();
			return allDoctors;
		}
	}
}
