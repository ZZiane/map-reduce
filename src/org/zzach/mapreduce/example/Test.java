package org.zzach.mapreduce.example;


import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.zzach.mapreduce.models.Data;
import org.zzach.mapreduce.tasks.MapWorker;
import org.zzach.mapreduce.tasks.ReduceWorker;
import org.zzach.mapreduce.tasks.ShuffleWorker;
import static org.zzach.mapreduce.utils.Common.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Test {
	
	
	public Test() {
		execution();
	}
	

	
	
	
	
	void execution(){
		 
		 String inputFile = "resources/data.txt";
		 
		 List<List<Data<String, Integer>>> MapOutput = new Vector<>();
		 List<List<Data<String, List<Integer>>>> shuffledOutput = new Vector<>();
		 List<Data<String, Integer>> finalOutput = new Vector<>();
		 initialiseList(shuffledOutput, 4);
		 initialiseList(MapOutput, 14);
		 
		 int numChunks = 8;

		 
		 List<String> inputChunks = fileDivider(inputFile, numChunks);
		 
		 List<Thread> threads = new Vector<>();
		 for (int i = 0; i < 8; i++) {
			 Thread t = new Thread(new MapWorker(inputChunks.get(i), MapOutput.get(i)));
			 threads.add(t);
			 t.start();       
	     }
		 
		 waitActions(threads);
		 
		 MapOutput = distributObject(MapOutput, 4);
		 
		 for (int i = 0; i < 4; i++) {
			 Thread t = new Thread(new ShuffleWorker(MapOutput.get(i), shuffledOutput.get(i)));
			 threads.add(t);
	         t.start();
		 }
		 waitActions(threads);
		 
		 
		 List<Data<String, List<Integer>>> mergedList =  mergeList(shuffledOutput);
		 
		 new ReduceWorker(mergedList, finalOutput).run();
	
		 
	    for (Data<String, Integer> d : finalOutput)
	    {
			 System.out.println(d.getKey() + " => "+d.getValue());			
		}
	     
	     
		
	}
	
	
//	private List mergeList(List... w) {
//		List v = new Vector<>();
//		for(int i=0; i<w.length;i++) {
//			v.addAll(w[i]);
//		}
//		return v;
//	}
	
	private List mergeList(List<?> w) {
		List v = new Vector<>();
		int k = w.size();
		for(int i=0; i<k ;i++) {
			v.addAll((Collection) w.get(i));
		}
		return v;
	}
	
	private List<List<Data<String, Integer>>> distributObject(List<List<Data<String, Integer>>> arr,int nbrArr) {
		List<List<Data<String, Integer>>> list = listCreator(nbrArr);
		for(List<Data<String, Integer>> d : arr) {
			for(Data<String, Integer> data : d) {
				if(data.getKey().charAt(0) > 109) {
					list.get(0).add(data);
				}
				else {
					list.get(1).add(data);
				}
			}
		}
		return list;
	}
	
	private List<List<Data<String, Integer>>> listCreator(int nbr){
		List<List<Data<String, Integer>>> arr = new Vector<>();
		for(int i=0;i<nbr;i++) {
			arr.add(new Vector<>());
		}
		return arr;
	}
	
	private void initialiseList(List w,int num) {
	 for(int i =0;i<num ;i++) {
		 w.add(new Vector<>());
	 }
	}
	
	private void waitActions(List<Thread> threads) {
		 for( Thread t : threads) {
			 try {
				t.join();
			} catch (Exception e) {}
		 }
		 threads.clear();
	}
	
	public static void main(String[] args){
      new Test(); 
    }
}


