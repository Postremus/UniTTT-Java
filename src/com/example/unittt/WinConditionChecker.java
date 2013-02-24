package com.example.unittt;

public class WinConditionChecker {

	private enum Directories
    {
        Up,
        Down,
        Left,
        Right,
        LeftUp,
        LeftDown,
        RightUp,
        RightDown
    }
	
	private int _winCondition;
	private static WinConditionChecker _instance = new WinConditionChecker(3);
	
	private WinConditionChecker()
	{
		_winCondition = 3;
	}
	
	private WinConditionChecker(int condition)
	{
		_winCondition = condition;
	}
	
	public static WinConditionChecker getInstance()
	{
		return GetInstance(3);
	}
	
	public static WinConditionChecker GetInstance(int wincondition)
	{
		return _instance;
	}
	
	public int GetWinCondition()
	{
		return _winCondition;
	}
	
	
	public Boolean Check(Player player, Field field)
	{
		//Horizontal
        for (int y = 0; y < field.GetHeight(); y++)
            if (DoCheck(field, Directories.Right, player.GetSymbol(), new Vector2i(0, y)) == _winCondition)
                return true;

        //Vertikal
        for (int x = 0; x < field.GetWidth(); x++)
            if (DoCheck(field, Directories.Down, player.GetSymbol(), new Vector2i(x, 0)) == _winCondition)
                return true;

        // Diagonal
        // Oben Links zu unten Rechts
        for (int x = 0; x < field.GetWidth(); x++)
        {
            for (int y = 0; y < field.GetHeight(); y++)
            {
                if (x + (_winCondition - 1) < field.GetWidth() && y + (_winCondition - 1) < field.GetHeight())
                {
                    if (DoCheck(field, Directories.RightDown, player.GetSymbol(), new Vector2i(x, y)) == _winCondition)
                        return true;
                }
            }
        }

        // Oben Rechts zu unten Links
        for (int x = 0; x < field.GetHeight(); x++)
        {
            for (int y = 0; y < field.GetHeight(); y++)
            {
                if ((x - (_winCondition - 1) >= 0) && y - (_winCondition - 1) < field.GetHeight())
                {
                    if (DoCheck(field, Directories.LeftDown, player.GetSymbol(), new Vector2i(x, y)) == _winCondition)
                        return true;
                }
            }
        }
        return false;
	}
	
	private int DoCheck(Field field, Directories dir, char player, Vector2i from)
    {
        int counter = 0;
        for (int a = 0; a < _winCondition; a++)
        {
            if (field.GetField(from) == player)
                counter++;
            else break;
            from = NextField(dir, from);
        }
        return counter;
    }

	private Vector2i NextField(Directories dir, Vector2i vect)
    {
        if (dir == Directories.Right)
            vect.x++;
        else if (dir == Directories.Left)
            vect.x--;
        else if (dir == Directories.Up)
            vect.y--;
        else if (dir == Directories.Down)
            vect.y++;
        else if (dir == Directories.LeftUp)
        {
            vect.x--;
            vect.y--;
        }
        else if (dir == Directories.LeftDown)
        {
            vect.x--;
            vect.y++;
        }
        else if (dir == Directories.RightUp)
        {
            vect.x++;
            vect.y--;
        }
        else if (dir == Directories.RightDown)
        {
            vect.y++;
            vect.y++;
        }
        return vect;
    }
}
