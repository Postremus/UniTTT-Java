package com.example.unittt;

public class WinCounterSource extends GenerallSource<IWinCounterListener>{
	public void FireEvent()
	{
		for (IWinCounterListener listener : super.GetListeners())
		{
			listener.WinCounterChanged();
		}
	}
}
