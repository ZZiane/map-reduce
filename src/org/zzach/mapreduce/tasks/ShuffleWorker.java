package org.zzach.mapreduce.tasks;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.zzach.mapreduce.models.Data;

public class ShuffleWorker implements Runnable{
	
	private List<Data<String, Integer>> input;
	private List<Data<String, List<Integer>>> output;
	
	public ShuffleWorker(List<Data<String, Integer>> input, List<Data<String, List<Integer>>> output) {
		this.input = input;
		this.output = output;
	}
	
	public void run() {
		for(Data<String, Integer> d : input) {
			boolean test = false;
				for(Data<String, List<Integer>> din : output) {
					
					if(d.getKey().equals(din.getKey())) {
						List<Integer> v =  din.getValue();
						v.add(d.getValue());
						test = true;
						break;
					}
				}
				if(!test) {
					Data<String, List<Integer>> data = new Data<>();
					data.setKey(d.getKey());
					data.setValue(new Vector<>());
					data.getValue().add(d.getValue());
	        	    output.add(data);
				}
		}
		Collections.sort(output, (a1,a2) -> a1.getKey().compareTo(a2.getKey()));
	}
}

