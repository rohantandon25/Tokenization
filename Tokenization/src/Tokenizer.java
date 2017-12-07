import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;


public class Tokenizer {

	ArrayList<String> stopWords;
	ArrayList<String> words;
	ArrayList<String> vowels;
	ArrayList<String> doubles;
	ArrayList<Character> punct;
	HashMap<Integer, Integer> graph;

	public Tokenizer(){
		stopWords = new ArrayList<String>();
		words = new ArrayList<String>();
		doubles = new ArrayList<String>();
		vowels = new ArrayList<String>();
		graph = new HashMap<Integer, Integer>();
		vowels.add("a");vowels.add("e");vowels.add("i");vowels.add("o");vowels.add("u");
		doubles.add("ll"); doubles.add("ss"); doubles.add("zz");
		punct = new ArrayList<Character>();
		punct.add('\'');punct.add(':');punct.add(',');punct.add('-');punct.add('!');punct.add('_');
		punct.add('(');punct.add(')');punct.add('?');punct.add(';');punct.add('\"');punct.add(' ');punct.add('/');
		//punct.add('.');
	}

	/**
	 * 
	 * @param inputFile
	 * @return arraylist of tokenized words, which are delimited by punctuations
	 * @throws IOException
	 */


	public ArrayList<String> tokens(File inputFile, ArrayList<String> list) throws IOException{
		int newwords = 0;
		int totalwords = 0;
		graph.put(newwords, totalwords);
		ArrayList<String> ll = list;
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		File file1 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/x.csv");
		File file2 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/y.csv");
		BufferedWriter writer1 = new BufferedWriter(new FileWriter(file1));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(file2));
		
		String line;
		while((line = bufferedReader.readLine()) != null){
			line=line+" ";
			char[] array = line.toCharArray();
			String temp = "";
			for(int i=0;i<line.length();i++){
				char ch=line.charAt(i);
				if((ch>='A' && ch<='Z') || (ch >='a' && ch<='z') || (ch >= '0' && ch<='9')||ch=='.'){
					if(ch=='.'){
						continue;
					}
					else{
						temp=temp+ch;
					}
				}
				else{
					if(temp.length()!=0){
						ll.add(temp.toLowerCase());
						if(!words.contains(temp)){
							words.add(temp);
							newwords++;
							totalwords++;
							writer1.write(newwords+"\n");
							writer2.write(totalwords+"\n");
						}
						else{
							totalwords++;
							writer1.write(newwords+"\n");
							writer2.write(totalwords+"\n");
						}
						temp = "";
					}
				}
			}
		}
		bufferedReader.close();
		return ll;
	}



	/**
	 * 
	 * @param file (lemur stopwords)
	 * @return arraylist words, removing all the stopwords
	 * @throws IOException
	 * 
	 */
	public ArrayList<String> stopper(ArrayList<String> list, File file) throws IOException{
		ArrayList<String> stopped = list;
		ArrayList<String> stop = new ArrayList<String>();
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while((line = bufferedReader.readLine()) != null){
			stopWords.add(line);
		}

		for(int i=0;i<stopped.size();i++){
			String temp  = stopped.get(i);
			//System.out.println(i + " "+ temp);
			if(!stopWords.contains(stopped.get(i))){
				stop.add(stopped.get(i));
			}
		}
		return stop;
	}

	public ArrayList<String> porter1a(ArrayList<String> list){
		ArrayList<String> porter = list;
		ArrayList<String> porterOut = new ArrayList<String>();
		for(String str: porter){
			if(str.endsWith("sses")){
				str = str.substring(0, str.length()-3);
				str = str + "ss";
				porterOut.add(str);
			}
			else if(str.endsWith("s") && str.length()>2 && !vowels.contains(str.charAt(str.length()-2))){
				str = str.substring(0, str.length()-1);
				porterOut.add(str);
			}
			else if(str.endsWith("ied") || str.endsWith("ies")){
				if(str.length()>4){
					str = str.substring(0, str.length()-2);
					str = str + "i";
					porterOut.add(str);
				}
				else{
					str = str.substring(0, str.length()-2);
					str = str + "ie";
					porterOut.add(str);
				}
			}
			else if(str.endsWith("us") || str.endsWith("ss")){
				//do nothing
				porterOut.add(str);
			}
			else{
				porterOut.add(str);
			}
		}
		return porterOut;
	}

	public ArrayList<String> porter1b(ArrayList<String> list){
		for(String str: list){
			if(str.endsWith("eed")){
				str = str.substring(0, str.length()-2);
				if(!vowels.contains(str.charAt(str.length()-1))){
					str = str + "ee";
				}
			}

			else if(str.endsWith("eedly")){
				str = str.substring(0, str.length()-4);
				if(!vowels.contains(str.charAt(str.length()-1))){
					str = str + "ee";
				}
			}

			else if(str.endsWith("ed")){
				str = str.substring(0, str.length()-1);
				if(str.endsWith("at") || str.endsWith("bl") || str.endsWith("iz")){
					str = str + "e";
				}
				if(str.length()>3){
					char secondlast = str.charAt(str.length()-1);
					char last = str.charAt(str.length()-2);
					StringBuilder sb = new StringBuilder();
					sb.append(secondlast);
					sb.append(last);
					if(last == secondlast && !doubles.contains(sb.toString())){
						str = str.substring(0, str.length()-1);
					}
					if(str.length()<=3){
						str = str + "e";
					}
				} 

			}
			else if(str.endsWith("edly")){
				str = str.substring(0, str.length()-3);
				if(str.endsWith("at") || str.endsWith("bl") || str.endsWith("iz")){
					str = str + "e";
				}
				char secondlast = str.charAt(str.length()-1);
				char last = str.charAt(str.length()-2);
				StringBuilder sb = new StringBuilder();
				sb.append(secondlast);
				sb.append(last);
				if(last == secondlast && !doubles.contains(sb.toString())){
					str = str.substring(0, str.length()-1);
				}
				if(str.length()<=3){
					str = str + "e";
				}

			}
			else if(str.endsWith("ing") && str.length()>3){
				str = str.substring(0, str.length()-3);
				if(str.endsWith("at") || str.endsWith("bl") || str.endsWith("iz")){
					str = str + "e";
				}
				if(str.length()>2){
					char secondlast = str.charAt(str.length()-2);
					char last = str.charAt(str.length()-1);
					StringBuilder sb = new StringBuilder();
					sb.append(secondlast);
					sb.append(last);
					if(last == secondlast && !doubles.contains(sb.toString())){
						str = str.substring(0, str.length()-1);
					}
					if(str.length()<=3){
						str = str + "e";
					}
				}
				

			}
			else if(str.endsWith("ingly")){
				str = str.substring(0, str.length()-4);
				if(str.endsWith("at") || str.endsWith("bl") || str.endsWith("iz")){
					str = str + "e";
				}
				char secondlast = str.charAt(str.length()-1);
				char last = str.charAt(str.length()-2);
				StringBuilder sb = new StringBuilder();
				sb.append(secondlast);
				sb.append(last);
				if(last == secondlast && !doubles.contains(sb.toString())){
					str = str.substring(0, str.length()-1);
				}
				if(str.length()<=3){
					str = str + "e";
				}

			}
		}
		return list;
	}

	public String abbreviations(String str){
		int periodCount = 0;
		for(int j=0;j<str.length();j++){
			if(str.charAt(j)=='.'){
				periodCount++;
			}
		}
		if(periodCount>1 && str.length()<=6){
			String newWord = str.replace('.', ' ');
			newWord = newWord.replaceAll("\\s+","");
			return newWord;
		}
		else{
			return str.substring(0, str.length());
		}
	}

}


