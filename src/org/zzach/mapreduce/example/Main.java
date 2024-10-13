package org.zzach.mapreduce.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

	public Main() {
		
		exp(prepareString("resources/data2.txt"));
		
	}
	
	@SuppressWarnings("resource")
	private String prepareString(String source) {
		
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(source));
	        String line;
			line = reader.readLine();
	        while (line != null) {
	            sb.append(line+" ");
	            line = reader.readLine();		
	        }
		} catch (Exception e) {}
		return sb.toString();
	}
	
	private void exp(String s){
		HashMap<String, Integer> counter = new HashMap<>();
		String[] mots = s.split(" ");
		for(String m : mots) {
			if(counter.containsKey(m)) {
				int i = counter.get(m)+1;
				counter.put(m, i);
				
			}
			else {
				counter.put(m, 1);
			}
			
		}
		
		counter.forEach((k,v) -> System.out.println(k +" : "+v));
		
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
