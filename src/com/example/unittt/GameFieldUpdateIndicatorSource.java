package com.example.unittt;

public class GameFieldUpdateIndicatorSource extends GenerallSource<IGameFieldUpdateIndicator>{
	public void FireEvent()
	{
		for (IGameFieldUpdateIndicator listener : super.GetListeners())
		{
			listener.StartUpdate();
		}
	}
}
