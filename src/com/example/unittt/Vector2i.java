package com.example.unittt;

public class Vector2i {
	public int x;
	public int y;
	
	public Vector2i()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static Vector2i FromIndex(int idx, int width, int height)
	{
		Vector2i vect = null;
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (x * width + y == idx)
                    vect = new Vector2i(x, y);
            }
        }
        return vect;
	}
}
