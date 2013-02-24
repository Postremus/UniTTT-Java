package com.example.unittt;

import java.util.Random;

public class FieldHelper {
	static Random rnd = new Random();
	
	public static int GetRandomMove(Field field)
	{
		if (!field.HasEmptyFields())
		{
			return -1;
		}
		while (true)
		{
			int idx = rnd.nextInt(field.GetLength());
			if (field.IsFieldEmpty(idx))
			{
				return idx;
			}
		}
	}
}
