package com.example.unittt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FieldRegion implements Iterable<FieldPlaceData>, Iterator<FieldPlaceData> {
	private List<FieldPlaceData> _fieldData;
	private int _iteratorPos;
	
	public FieldRegion()
	{
		_fieldData = new ArrayList<FieldPlaceData>();
		_iteratorPos = 0;
	}
	
	public void Add(int locationInField, char fieldValue)
    {
		_fieldData.add(new FieldPlaceData(locationInField, fieldValue));
    }
	
	public int Count()
	{
		return _fieldData.size();
	}
	
	@Override
	public boolean hasNext() {
		return _iteratorPos < _fieldData.size()-1;
	}

	@Override
	public FieldPlaceData next() {
		FieldPlaceData ret = _fieldData.get(_iteratorPos);
		_iteratorPos++;
		return ret;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<FieldPlaceData> iterator() {
		return this;
	}
	
}
