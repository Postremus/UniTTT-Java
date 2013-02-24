package com.example.unittt;

public class PlayerChangedSource extends GenerallSource<IPlayerChangedListener>{
	public void FireEvent()
	{
		for (IPlayerChangedListener listener : super.GetListeners())
		{
			listener.PlayerChange();
		}
	}
}
