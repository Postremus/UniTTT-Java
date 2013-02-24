package com.example.unittt;

public class EnemyTurnStateChangedSource extends GenerallSource<IEnemyTurnStateChangedListener>{
	public void FireEvent(Boolean state)
	{
		for (IEnemyTurnStateChangedListener listener : super.GetListeners())
		{
			listener.EnemyTurn(state);
		}
	}
}
