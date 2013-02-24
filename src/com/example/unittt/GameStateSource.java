package com.example.unittt;

public class GameStateSource extends GenerallSource<IGameStateListener> {
	public void FireEvent(GameStates state)
	{
		for (IGameStateListener listener : super.GetListeners())
		{
			listener.RefreshgameState(state);
		}
	}
}
