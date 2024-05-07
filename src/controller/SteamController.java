package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SteamController {

	private File dataset;

	public SteamController() {
		super();
	}

	public SteamController(File dataset) {
		this.dataset = dataset;
	}

	public SteamController(String datasetPath) {
		this.dataset = new File(datasetPath);
	}

	public File getDataset() {
		return dataset;
	}

	public void setDataset(File dataset) {
		this.dataset = dataset;
	}

	public void setDataset(String datasetPath) {
		this.dataset = new File(datasetPath);
		;
	}

	public void showAvg(int year, String month, double minAverage) throws Exception {
		if (!dataset.exists()) {
			throw new Exception("Invalid dataset file!");
		}
		BufferedReader reader = new BufferedReader(new FileReader(dataset));
		StringBuilder sb = new StringBuilder();
		reader.readLine();
		String line = reader.readLine();
		while (line != null) {
			String[] l = line.split(",");
			if (l[2].equals(month)) {
				int yr = Integer.parseInt(l[1]);
				if (yr == year) {
					double avg = Double.parseDouble(l[3]);
					if (avg >= minAverage) {
						sb.append(l[0] + " | " + avg + "\n");
					}
				}
			}
			line = reader.readLine();
		}
		System.out.println(sb);
		reader.close();
	}

	public void newFile(int year, String month, String path, String filename) throws Exception {
		if (!dataset.exists()) {
			throw new Exception("Invalid dataset file!");
		}
		File file = new File(path + "\\" + filename + ".csv");
		if (!file.getParentFile().exists()) {
			throw new Exception("Invalid path!");
		}
		BufferedReader reader = new BufferedReader(new FileReader(dataset));
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("gamename;avg");
		writer.newLine();
		StringBuilder sb = new StringBuilder();
		reader.readLine();
		String line = reader.readLine();
		while (line != null) {
			String[] l = line.split(",");
			if (l[2].equals(month)) {
				int yr = Integer.parseInt(l[1]);
				if (yr == year) {
					writer.write(l[0] + ";" + l[3]);
					writer.newLine();
				}
			}
			line = reader.readLine();
		}
		System.out.println(sb);
		reader.close();
		writer.close();

	}

}
