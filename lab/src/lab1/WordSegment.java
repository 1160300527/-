package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WordSegment {
	private Set<String>Dictionary;
	private int maxLength;
	public List<String> Segment(List<String>content,Set<String>Dictionary,int maxLength){
		this.Dictionary = Dictionary;
		this.maxLength = maxLength;
		List<String>splitLines = new ArrayList<String>();
		for(String line:content) {
			splitLines.add(BM(line));
		}
		return splitLines;
	}
	
	
	public String FMM(String content) {
		StringBuffer split = new StringBuffer();
		int index = 0;
		int contentLength = content.length()-1;
		int length = 0;
		String word="";
		while(index<=contentLength) {
			if(content.toCharArray()[index]==' ') {
				index++;
				continue;
			}
			//System.out.print(index);
			length = contentLength-index>=maxLength ? maxLength : contentLength-index+1;
			word = content.substring(index, index+length);
			while(length>1) {
				if(Dictionary.contains(word))
					break;
				length--;
				word = content.substring(index, index+length);
			}
			//System.out.println(" "+length);
			index+=length;
			//split.append(word);
			if(word.equals("\r")||word.equals("\n")||word.equals(" ")||index>contentLength)
				continue;
			split.append("  ");
		}
		return split.toString();
	}
	
	
	public String BMM(String content) {
		StringBuffer split = new StringBuffer();
		int index = content.length();
		int length = 0;
		String word="";
		while(index>=1) {
			if(content.toCharArray()[index-1]==' ') {
				index--;
				continue;
			}
			//System.out.print(index);
			length = index >= maxLength ? maxLength : index;
			word = content.substring(index-length, index);
			while(length>1) {
				if(Dictionary.contains(word))
					break;
				length--;
				word = content.substring(index-length, index);
			}
			//System.out.println(" "+length);
			index -= length;
			split.insert(0, word);
			//System.out.println(word);
			if(word.equals("\r")||word.equals("\n")||word.equals(" ")||index<=0)
				continue;
			split.insert(0,"  ");
		}
		return split.toString();
	}
	
	
	public String BM(String content) {
		String split1 = FMM(content);
		String split2 = BMM(content);
		String words1[] = split1.split("  ");
		String words2[] = split2.split("  ");
		int length1 = words1.length;
		int length2 = words2.length;
		if(length1!=length2) {
			return length1>length2 ? split1 : split2;
		}
		int single1=0,single2=0;
		for(int i = 0; i < length1; i++) {
			if(words1[i].length()==1) {
				single1++;
			}
			if(words2[i].length()==1) {
				single2++;
			}
		}
		return single1<single2 ? split1:split2;
	}
	
	public void testSegment(List<String> split,List<String> goal) {
		int correct = 0;
		int indexResult = 0;
		int indexGoal = 0;
		int iterResult=0,iterGoal = 0;
		List<String> result = new ArrayList<String>();
		for(String line : split) {
			result.addAll(Arrays.asList(line.split("\\s+")));
		}
		//System.out.println(result);
		//System.out.println(goal);
		while(iterResult<result.size()&&iterGoal<goal.size()) {
			//System.out.println("indexResult:"+indexResult);
			//System.out.println("indexGoal:"+indexGoal);
			//System.out.print(word);
			//System.out.println(" "+goal.get(index));
			//System.out.println("correct:"+correct);
			String wordResult = result.get(iterResult);
			String wordGoal = goal.get(iterGoal);
			if(indexResult==indexGoal) {
				//System.out.println(wordResult+"  "+wordGoal);
				if(wordResult.equals(wordGoal)) {
					correct++;
					//System.out.println(wordResult+"  "+wordGoal);
				}
				indexResult+=wordResult.length();
				indexGoal+=wordGoal.length();
				iterResult++;
				iterGoal++;
				//System.out.println("indexResult:"+indexResult);
				//System.out.println("indexGoal:"+indexGoal);
			}
			else if(indexResult<indexGoal) {
				indexResult+=wordResult.length();
				iterResult++;
			}
			else {
				indexGoal+=wordGoal.length();
				iterGoal++;
			}
			//System.out.println("indexResult:"+indexResult);
			//System.out.println("indexGoal:"+indexGoal);
		}
		float precision = (float)correct/result.size();
		float recall = (float)correct/goal.size();
		float F1score = 2*precision*recall/(2*precision+recall);
		System.out.println("Precisn:"+precision);
		System.out.println("Recall:"+recall);
		System.out.println("F1-Score£º"+F1score);
	}
}
