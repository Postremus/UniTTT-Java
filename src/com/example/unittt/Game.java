package com.example.unittt;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity implements IGameStateListener, IWinCounterListener, IGameFieldClearerListener, IEnemyTurnStateChangedListener, IGameFieldUpdateIndicator{

	private GameLogik _game;
	private Boolean _hasEnd;
	private List<Button> buttons;
	private Boolean _enemyTurn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		_game = new GameLogik(new Player('X'), new AIPlayer('O'), new Field(3, 3));
		
		_hasEnd = false;
		_enemyTurn = false;
		_game.GetGameStateSource().Add(this);
		_game.GetWinCounterSource().Add(this);
		_game.GetGameFieldClearer().Add(this);
		_game.GetGameFieldUpdateIndicatorSource().Add(this);
		
		buttons = new ArrayList<Button>();
		List<Integer> buttonsId = new ArrayList<Integer>();
		buttonsId.add(R.id.button1);
		buttonsId.add(R.id.button2);
		buttonsId.add(R.id.button3);
		buttonsId.add(R.id.button4);
		buttonsId.add(R.id.button5);
		buttonsId.add(R.id.button6);
		buttonsId.add(R.id.button7);
		buttonsId.add(R.id.button8);
		buttonsId.add(R.id.button9);
		
		for (int btnid : buttonsId)
		{
			buttons.add((Button)findViewById(btnid));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

	
	public void Button1Pressed(View view)
	{
		callLogik(0, view);
	}
	
	public void Button2Pressed(View view)
	{
		callLogik(1, view);
	}
	
	public void Button3Pressed(View view)
	{
		callLogik(2, view);
	}
	
	public void Button4Pressed(View view)
	{
		callLogik(3, view);
	}
	
	public void Button5Pressed(View view)
	{
		callLogik(4, view);
	}
	
	public void Button6Pressed(View view)
	{
		callLogik(5, view);
	}
	
	public void Button7Pressed(View view)
	{
		callLogik(6, view);
	}
	
	public void Button8Pressed(View view)
	{
		callLogik(7, view);
	}
	
	public void Button9Pressed(View view)
	{
		callLogik(8, view);
	}
	
	private void callLogik(int idx, View view)
	{
		if (_hasEnd || _enemyTurn)
		{
			return;
		}
		
		_game.GetPlayer().Play(Vector2i.FromIndex(idx, 3, 3));
	}

	@Override
	public void RefreshgameState(GameStates state) {
		// TODO Auto-generated method stub
		_hasEnd = state != GameStates.Laufend;
		Toast t;
		if (state == GameStates.Unentschieden)
		{
			t = Toast.makeText(getBaseContext(), "Unentschieden.", Toast.LENGTH_SHORT);
		}
		else if (state == GameStates.Gewonnen)
		{
			t = Toast.makeText(getBaseContext(), "Du hast gewonnen. :)", Toast.LENGTH_SHORT);
		}
		else if (state == GameStates.Verloren)
		{
			t = Toast.makeText(getBaseContext(), "Du hast verloren. :(", Toast.LENGTH_SHORT);
		}
		else
		{
			t = Toast.makeText(getBaseContext(), "Du hast gar nichts!", Toast.LENGTH_SHORT);
		}
		t.show();
		
	}

	@Override
	public void WinCounterChanged() {
		int player1Counter = _game.GetPlayer1().GetWinCounter();
		int player2Counter = _game.GetPlayer2().GetWinCounter();
		
		((TextView)findViewById(R.id.win)).setText(String.valueOf(player1Counter));
		((TextView)findViewById(R.id.lose)).setText(String.valueOf(player2Counter));
		((TextView)findViewById(R.id.draw)).setText(String.valueOf(_game.GetGamesPlayed()-player1Counter-player2Counter));
	}
	
	public void ActivityGameClicked(View view)
	{
		if (this._hasEnd)
		{
			_game.RestartGame();
		}
	}

	@Override
	public void ClearGameField() {
		for (Button btn : buttons)
		{
			btn.setText("");
		}
		_hasEnd = false;
	}

	@Override
	public void EnemyTurn(Boolean state) {
		_enemyTurn = state;
	}

	@Override
	public void StartUpdate() {
		
		((TextView)findViewById(R.id.win)).post(new Runnable() 
		{
			public void run()
			{
				for (int i=0; i<9;i++)
				{
					buttons.get(i).setText(String.valueOf(_game.GetField().GetField(i)));
				}
			}
		});
	}
}
