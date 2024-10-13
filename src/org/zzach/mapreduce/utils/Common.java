package org.zzach.mapreduce.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

public class Common {

	public static List<String> fileDivider(String inputFile, int numChunks) {
        List<String> inputChunks = new Vector<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	        String line = reader.readLine();
	        StringBuilder sb = new StringBuilder();
	        int chunkSize = 0;
	        while (line != null) {
	            sb.append(line+"\n");
	            chunkSize++;
	            if (chunkSize >= numChunks) {
	                inputChunks.add(sb.toString());
	                sb = new StringBuilder();
	                chunkSize = 0;
	            }
	            line = reader.readLine();
	        }
	        reader.close();
	        if (chunkSize > 0) {
	            inputChunks.add(sb.toString());
	        }
		} catch (Exception e) {
			
		}
        return inputChunks;
    }
	
}
