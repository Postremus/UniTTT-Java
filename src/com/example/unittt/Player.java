package com.example.unittt;

public class Player {

	private char _symbol;
	private PlayerMovedSource _movedSource;
	
	public Player(char symbol)
	{
		_symbol = symbol;
		_movedSource = new PlayerMovedSource();
	}
	
	public char GetSymbol()
	{
		return _symbol;
	}
	
	public PlayerMovedSource GetMovedSource()
	{
		return _movedSource;
	}
	
	public void Play(Vector2i p)
	{
		_movedSource.FireEvent(p);
	}
}
