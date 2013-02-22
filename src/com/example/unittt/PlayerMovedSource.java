package com.example.unittt;
import java.util.ArrayList;
import java.util.List;

public class PlayerMovedSource {
	private List<IPlayerMovedListener> _listeners;
	
	public PlayerMovedSource()
	{
		_listeners = new ArrayList<IPlayerMovedListener>();
	}
	
	public void Add(IPlayerMovedListener listener)
	{
		_listeners.add(listener);
	}
	
	public void Remove(IPlayerMovedListener listener)
	{
		_listeners.remove(_listeners);
	}
	
	public void FireEvent(Vector2i p)
	{
		for (IPlayerMovedListener listener : _listeners)
		{
			listener.PlayerMoved(p);
		}
	}
}
