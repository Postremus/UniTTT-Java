package com.example.unittt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Game extends Activity implements IGameStateListener{

	private GameLogik _game;
	private Boolean _hasEnd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		_game = new GameLogik(new Player('X'), new Player('O'), new Field(3, 3));
		_hasEnd = false;
		_game.GetGameStateSource().Add(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

	
	public void Button1Pressed(View view)
	{
		callLogik(1, view);
	}
	
	public void Button2Pressed(View view)
	{
		callLogik(2, view);
	}
	
	public void Button3Pressed(View view)
	{
		callLogik(3, view);
	}
	
	public void Button4Pressed(View view)
	{
		callLogik(4, view);
	}
	
	public void Button5Pressed(View view)
	{
		callLogik(5, view);
	}
	
	public void Button6Pressed(View view)
	{
		callLogik(6, view);
	}
	
	public void Button7Pressed(View view)
	{
		callLogik(7, view);
	}
	
	public void Button8Pressed(View view)
	{
		callLogik(8, view);
	}
	
	public void Button9Pressed(View view)
	{
		callLogik(9, view);
	}
	
	private void callLogik(int idx, View view)
	{
		if (_hasEnd)
		{
			return;
		}
		
		
		Button btn = (Button)view;
		btn.setText(String.valueOf(_game.GetPlayer().GetSymbol()));
		
		_game.GetPlayer().Play(Vector2i.FromIndex(idx, 3, 3));
	}

	@Override
	public void RefreshgameState(GameStates state) {
		// TODO Auto-generated method stub
		_hasEnd = state != GameStates.Laufend;
		
		Toast t = Toast.makeText(getBaseContext(), "Fertig.", Toast.LENGTH_SHORT);
		t.show();
	}
}
