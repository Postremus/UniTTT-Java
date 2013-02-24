package com.example.unittt;

public class Player {

	private char _symbol;
	private PlayerMovedSource _movedSource;
	private int _winCounter;
	
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
	
	public int GetWinCounter()
	{
		return _winCounter;
	}
	
	public void SetWinCounter(int val)
	{
		_winCounter = val;
	}
	
	public void Play(Vector2i p)
	{
		_movedSource.FireEvent(p);
	}
	
	
	public static char PlayerChange(char curr)
    {
        return curr == 'X' ? 'O' : 'X';
    }
}
