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
		read.readDictionary("lab/dictionary/dict.txt");
		String filename = "msr.txt";
		List<String> content = read.readContent("lab/test/"+filename);
		//System.out.println(content);
		WordSegment seg = new WordSegment();
		long start = System.currentTimeMillis();
		List<String> split = seg.Segment(content, read.getDictionary(),read.getLength());
		long end = System.currentTimeMillis();
		System.out.println("cost time:"+(float)(end-start)/1000+"s");
		new test().writeResult(split,"lab/result/"+filename);
		seg.testSegment(split, read.getGoal("lab/goal/"+filename));
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
