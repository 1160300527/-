package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadFile {
	private Set<String> dictionary = new HashSet<String>();
	private int maxLength = 0;
	
	public Set<String>getDictionary(){
		return dictionary;
	}
	
	public void readDictionary(String filePath) {
		File file = new File(filePath);
		try {
			FileInputStream reader = new FileInputStream(file);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(reader,"utf-8"));
			String word = "";
			while((word=buffer.readLine())!=null) {
				if(word.length()>maxLength) {
					maxLength = word.length();
				}
				dictionary.add(word);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public List<String> readContent(String path) {
		List<String> content = new ArrayList<String>();
		File file = new File(path);
		try {
			FileInputStream reader = new FileInputStream(file);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(reader,"utf-8"));
			String line = "";
			while((line=buffer.readLine())!=null) {
				content.add(line);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return content;
	}
	
	
	public List<String> getGoal(String path){
		List<String>goal = new ArrayList<String>();
		File file = new File(path);
		try {
			FileInputStream reader = new FileInputStream(file);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(reader,"utf-8"));
			String line = "";
			while((line=buffer.readLine())!=null) {
				goal.addAll(Arrays.asList(line.split("\\s+")));
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return goal;
	}
	public int getLength() {
		return maxLength;
	}
}
