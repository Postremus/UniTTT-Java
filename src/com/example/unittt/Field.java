package com.example.unittt;

public class Field {

	private char[][] _fieldData;
	private int _width;
	private int _height;
	
	public Field(int width, int height)
	{
		_width = width;
		_height = height;
		_fieldData = new char[_width][_height];
		BrettInit();
	}
	
	private void BrettInit()
	{
		for (int x=0;x<_width;x++)
		{
			for (int y=0;y<_height;y++)
			{
				_fieldData[x][y] = ' ';
			}
		}
	}
	
	public int GetWidth()
	{
		return _width;
	}
	
	public int GetHeight()
	{
		return _height;
	}
	
	public void SetField(Vector2i p, char player)
	{
		_fieldData[p.x][p.y] = player;
	}
	
	public char GetField(Vector2i p)
	{
		return _fieldData[p.x][p.y];
	}
	
	public Boolean HasEmptyFields()
	{
		for (int x=0; x < _width;x++)
		{
			for (int y=0; y < _height;y++)
			{
				if (_fieldData[x][y] == ' ')
				{
					return true;
				}
			}
		}
		return true;
	}
}
