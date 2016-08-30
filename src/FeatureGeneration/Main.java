package FeatureGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Util.PreProcessUtility;

public class Main {

	public static void main(String[] args) throws IOException {
		String file;
		Scanner sc = new Scanner(System.in);
		System.out.println("Plz enter the file u wanna handle!");
		file = sc.nextLine();
		
		File input = new File(file);
		
		Process p = new Process();
		p.featureMerge();
		
	}

}
