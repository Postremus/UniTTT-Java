package com.example.unittt;

public class EnemyThread implements Runnable{
	private Player _enemy;
	private Field _field;
	
	public EnemyThread()
	{
	}
	
	public void SetEnemy(Player enemy)
	{
		_enemy = enemy;
	}
	
	public void SetField(Field field)
	{
		_field = field;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (_enemy instanceof AIPlayer)
		{
			((AIPlayer)_enemy).Play(_field);
		}
	}
}
