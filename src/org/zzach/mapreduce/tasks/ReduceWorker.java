package org.zzach.mapreduce.tasks;

import java.util.List;

import org.zzach.mapreduce.models.Data;

public class ReduceWorker implements Runnable{
	
	private List<Data<String, List<Integer>>> input;
	private List<Data<String, Integer>> output;
	
	public ReduceWorker(List<Data<String, List<Integer>>> input , List<Data<String, Integer>> output) {
		this.input = input;
		this.output = output;
	}

	public void run() {
		for(Data<String, List<Integer>> d : input) {
    		List<Integer> datas = d.getValue();
    		int sum = 0;
    		for(Integer i : datas) {
    				sum += i;
    		}
		    Data<String, Integer> data = new Data<>();
		    data.setKey(d.getKey());
		    data.setValue(sum);
		    output.add(data);
		}
	}

}


