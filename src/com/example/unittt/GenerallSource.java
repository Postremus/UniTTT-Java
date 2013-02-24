package com.example.unittt;

import java.util.ArrayList;
import java.util.List;

public class GenerallSource<T> {
	private List<T> _listeners;
	
	public GenerallSource()
	{
		_listeners = new ArrayList<T>();
	}
	
	public void Add(T listener)
	{
		_listeners.add(listener);
	}
	
	public void Remove(T listener)
	{
		_listeners.remove(_listeners);
	}
	
	public List<T> GetListeners()
	{
		return _listeners;
	}
}
