package com.example.unittt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field implements Iterable<FieldPlaceData>, Iterator<FieldPlaceData>{

	private char[][] _fieldData;
	private int _width;
	private int _height;
	private int _iteratorPos;
	
	public Field(int width, int height)
	{
		_width = width;
		_height = height;
		_fieldData = new char[_width][_height];
		_iteratorPos = 0;
		BrettInit();
	}
	
	private Field(char[][] fieldData, int width, int height)
	{
		_fieldData = fieldData;
		_width = width;
		_height = height;
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
	
	public int GetLength()
	{
		return _width * _height;
	}
	
	public void SetField(Vector2i p, char player)
	{
		if (!IsPointInField(p))
		{
			return;
		}
		_fieldData[p.x][p.y] = player;
	}
	
	public void SetField(int idx, char player)
	{
		SetField(Vector2i.FromIndex(idx, _width, _height), player);
	}
	
	public char GetField(Vector2i p)
	{
		if (!IsPointInField(p))
		{
			return ' ';
		}
		return _fieldData[p.x][p.y];
	}
	
	public char GetField(int idx)
	{
		return GetField(Vector2i.FromIndex(idx, _width, _height));
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
		return false;
	}
	
	public Boolean IsFieldEmpty(int idx)
	{
		return GetField(idx) == ' ';
	}
	
	public Boolean IsFieldEmpty(Vector2i p)
	{
		return GetField(p) == ' ';
	}
	
	public Boolean IsPointInField(int idx)
	{
		return IsPointInField(Vector2i.FromIndex(idx, _width, _height));
	}
	
	public Boolean IsPointInField(Vector2i p)
	{
		return p.x > -1 && p.y > -1 && p.x < _width && p.y < _height;
	}
	
	public List<FieldRegion> GetRegions()
	{
		List<FieldRegion> ret = new ArrayList<FieldRegion>();
        for (int y = 0; y < _height; y++)
        {
            ret.add(Column(y));
        }
        for (int x = 0; x < _width; x++)
        {
            ret.add(Row(x));
            ret.add(LeftTopToRightButtom(x));
            ret.add(RightTopToLeftButtom(x));
        }
        return ret;
	}
	
	private FieldRegion Row(int x)
    {
		FieldRegion ret = new FieldRegion();

        for (int y = 0; y < _height; y++)
        {
            ret.Add(x * _width + y, GetField(new Vector2i(x, y)));
        }
        return ret;
    }

	private FieldRegion Column(int y)
    {
        FieldRegion ret = new FieldRegion();
        for (int x = 0; x < _width; x++)
        {
            ret.Add(x * _height + y, GetField(new Vector2i(x, y)));
        }
        return ret;
    }

    private FieldRegion LeftTopToRightButtom(int x)
    {
        FieldRegion ret = new FieldRegion();
        for (int y = 0; y < _height; y++)
        {
            if (x < 0 || x >= _width)
            {
                break;
            }
            ret.Add(x * _width + y, GetField(new Vector2i(x, y)));
            x++;
        }
        return ret;
    }

    private FieldRegion RightTopToLeftButtom(int x)
    {
        FieldRegion ret = new FieldRegion();
        for (int y = 0; y < _height; y++)
        {
            if (x < 0 || x >= _width)
            {
                break;
            }
            ret.Add(x * _width + y, GetField(new Vector2i(x, y)));
            x--;
        }
        return ret;
    }
    
    public Field Clone()
    {
    	return new Field(this._fieldData.clone(), this._width, this._height);
    }
    
	@Override
	public Iterator<FieldPlaceData> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return _iteratorPos < GetLength()-1;
	}

	@Override
	public FieldPlaceData next() {
		// TODO Auto-generated method stub
		FieldPlaceData ret = new FieldPlaceData(_iteratorPos, GetField(_iteratorPos));
		_iteratorPos++;
		return ret;
	}

	public String ToString()
	{
		String ret = "";
		for	(int x=0;x<_width;x++)
		{
			for (int y=0;y<_height;y++)
			{
				ret += _fieldData[x][y];
			}
		}
		return ret;
	}
	
	public static Field FromString(String val, int width, int height)
	{
		Field ret = new Field(width, height);
		for (int i=0;i<width*height;i++)
		{
			ret.SetField(i, val.charAt(i));
		}
		return ret;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
