package com.example.unittt;

public class PlayerMovedSource extends GenerallSource<IPlayerMovedListener>{
	public void FireEvent(Vector2i p)
	{
		for (IPlayerMovedListener listener : super.GetListeners())
		{
			listener.PlayerMoved(p);
		}
	}
}
