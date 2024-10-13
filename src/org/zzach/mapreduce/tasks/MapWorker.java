package org.zzach.mapreduce.tasks;

import java.util.List;
import org.zzach.mapreduce.models.Data;

public class MapWorker implements Runnable {
	
    private String input;
    private List<Data<String, Integer>> output;

    public MapWorker(String input, List<Data<String, Integer>> output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
    		for(String s : input.split("\n")) {
    			if(!s.isBlank()) {
    				s =s.trim();
    				String[] data = s.split("\s+");
            		for(String in : data) {
            			Data<String, Integer> d = new Data<>();
            	    	d.setKey(in);
            	    	d.setValue(1);
                	    output.add(d);
            		}        	    	
    			}
        	}
    }
}