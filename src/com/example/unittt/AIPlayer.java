package com.example.unittt;

import java.util.List;

public class AIPlayer extends Player{
	
	public AIPlayer(char symbol) {
		super(symbol);
	}
	
	public void Play(Field field)
	{
		field = Field.FromString(field.ToString(), field.GetWidth(), field.GetHeight());
		int win_zug = TestForLineComplettings(field, super.GetSymbol());
        int block_zug = TestForLineComplettings(field, Player.PlayerChange(super.GetSymbol()));
        int set_zug = TestForBestPosition(field);

        int ret = -1;
        if (win_zug != -1)
        	if (field.IsFieldEmpty(win_zug))
        	{
        		ret = win_zug;
        	}
        if (block_zug != -1 && ret == -1)
        	if (field.IsFieldEmpty(block_zug))
        	{
        		ret = block_zug;
        	}
        if (set_zug != -1  && ret == -1)
        	if (field.IsFieldEmpty(set_zug))
        	{
        		ret = set_zug;
        	}
        if (ret == -1)
        	ret = FieldHelper.GetRandomMove(field);
        super.GetMovedSource().FireEvent(Vector2i.FromIndex(ret, field.GetWidth(), field.GetHeight()));
	}
	
	private int TestForLineComplettings(Field field, char player)
    {
        int ret = -1;
        for (int playerpos = 0; (playerpos < field.GetLength()) && (ret == -1); playerpos++)
        {
            if (field.IsFieldEmpty(playerpos))
            {
                field.SetField(playerpos, player);
                if ((WinConditionChecker.Check(new Player(player), field)))
                    ret = playerpos;
                field.SetField(playerpos, ' ');
            }
        }
        return ret;
    }

    private int TestForBestPosition(Field field)
    {
        int[] posis = new int[field.GetLength()];

        List<FieldRegion> fpanel = field.GetRegions();

        for (FieldRegion region : fpanel)
        {
            if (region.Count() >= WinConditionChecker.GetWinCondition())
            {
                for (FieldPlaceData data : region)
                {
                    if (data.GetFieldValue() == ' ')
                    {
                        posis[data.GetLocationInField()]++;
                    }
                }
            }
        }

        int ret = -1;
        for	(int i=0;i<posis.length;i++)
        {
        	if (posis[i] > ret)
        	{
        		ret = posis[i];
        	}
        }
        
        return ret;
    }
}
