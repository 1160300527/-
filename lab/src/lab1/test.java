package lab1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class test {
	public static void main(String args[]) {
		ReadFile read = new ReadFile();
		read.readDictionary("lab/test/dict.txt");
		List<String> content = read.readContent("lab/test/all_testing.txt");
		//System.out.println(content);
		WordSegment seg = new WordSegment();
		List<String> split = seg.Segment(content, read.getDictionary(),read.getLength());
		new test().writeResult(split,"lab/test/all_testing_result.txt");
		seg.testSegment(split, read.getGoal("lab/test/all_testing_goal.txt"));
	}
	
	public void writeResult(List<String> content,String filePath) {
		File file = new File(filePath);
		try {
			FileOutputStream output = new FileOutputStream(file);
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(output,"utf-8"));
			int i;
			for(i=0;i<content.size()-1;i++) {
				buffer.write(content.get(i)+"\r\n");
			}
			buffer.write(content.get(i));
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
