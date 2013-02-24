package com.example.unittt;

public class GameFieldClearerSource extends GenerallSource<IGameFieldClearerListener>{
	public void FireEvent()
	{
		for (IGameFieldClearerListener listener : super.GetListeners())
		{
			listener.ClearGameField();
		}
	}
}
