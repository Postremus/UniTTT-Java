package com.example.unittt;

public class FieldPlaceData {
	private int _locationInField;
    private char _fieldValue;

    public FieldPlaceData(int locationInField, char fieldValue)
    {
        _locationInField = locationInField;
        _fieldValue = fieldValue;
    }
    
    public int GetLocationInField()
    {
    	return _locationInField;
    }
    
    public char GetFieldValue()
    {
    	return _fieldValue;
    }
}
