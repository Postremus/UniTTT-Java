package com.example.unittt;
import java.util.ArrayList;
import java.util.List;


public class GameStateSource {
	private List<IGameStateListener> _listeners;
	
	public GameStateSource()
	{
		_listeners = new ArrayList<IGameStateListener>();
	}
	
	public void Add(IGameStateListener listener)
	{
		_listeners.add(listener);
	}
	
	public void Remove(IGameStateListener listener)
	{
		_listeners.remove(_listeners);
	}
	
	public void FireEvent(GameStates state)
	{
		for (IGameStateListener listener : _listeners)
		{
			listener.RefreshgameState(state);
		}
	}
}
