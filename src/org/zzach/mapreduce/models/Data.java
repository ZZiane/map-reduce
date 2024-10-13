package org.zzach.mapreduce.models;

public class Data<K,V> {

	private K key;
	private V value;
	
	public Data() {
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public void setKey(K key) {
		this.key = key;
	}
}
