package view;

import java.util.Scanner;

import controller.SteamController;

public class Main {

	public static void main(String[] args) throws Exception {

		SteamController controller = new SteamController("c:\\temp\\SteamCharts.csv");
		Scanner sc = new Scanner(System.in);

		int option = 1;
		do {
			try {
				option = actionsMenu(sc);
			
				switch (option) {
				case 1, 2:
					optionMenu(option, sc, controller);
					break;
				case 0:
					System.out.println("Ending...");
					break;
				default:
					throw new Exception("Invalid option!");
				}
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		} while (option != 0);

		sc.close();
	}

	public static int actionsMenu(Scanner sc) throws Exception {
		System.out.println("------ Steam Data ------");
		System.out.println("[1] Show active players average by game and month");
		System.out.println("[2] Create new file");
		System.out.println("[0] Exit");
		System.out.print("> ");
		try {
			int option = Integer.parseInt(sc.nextLine().trim());
			return option;
		} catch (NumberFormatException e) {
			throw new Exception("Invalid option!");
		}
	}

	public static void optionMenu(int option, Scanner sc, SteamController controller) throws Exception {
		if (option == 1) {
			int year;
			String month;
			double avg;
			try {
				System.out.print("Type a year: ");
				year = Integer.parseInt(sc.nextLine().trim());
				System.out.print("Type a month: ");
				month = sc.nextLine().trim();
				System.out.print("Type the min average to filter: ");
				avg = Double.parseDouble(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				throw new Exception("Invalid input!");
			}
			System.out.println();
			controller.showAvg(year, month, avg);

		} else if (option == 2) {
			System.out.print("Type a year: ");
			int year;
			try {
				year = Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				throw new Exception("Invalid input!");
			}
			System.out.print("Type a month: ");
			String month = sc.nextLine().trim();
			System.out.print("Type a path to save the file: ");
			String path = sc.nextLine().trim();
			System.out.print("Type the file name: ");
			String name = sc.nextLine().trim();
			controller.newFile(year, month, path, name);
			System.out.println("File created at " + path + "\\" + name + ".csv");
		}
	}

}
