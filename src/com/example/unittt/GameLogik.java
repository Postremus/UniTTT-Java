package com.example.unittt;
import java.util.List;
import java.util.ArrayList;

public class GameLogik implements IPlayerMovedListener {

	private Player _player;
	private Player _player1;
	private Player _player2;
	private Field _field;
	private GameStateSource _stateSource;
	private WinCounterSource _counterSource;
	private GameFieldClearerSource _fieldClearerSource;
	private int _gamesPlayed;
	private EnemyThread _enemyThread;
	private EnemyTurnStateChangedSource _turnStateChangedSource;
	private GameFieldUpdateIndicatorSource _updateIndicator;
	private PlayerChangedSource _playerChangedSource;
	
	public GameLogik(Player player1, Player player2, Field field)
	{
		player1.GetMovedSource().Add(this);
		player2.GetMovedSource().Add(this);
		
		_player = player1;
		_player1 = player1;
		_player2 = player2;
		_field = field;
		WinConditionChecker.SetWinCondition(_field.GetWidth());
		_stateSource = new GameStateSource();
		_counterSource = new WinCounterSource();
		_fieldClearerSource = new GameFieldClearerSource();
		_enemyThread = new EnemyThread();
		_turnStateChangedSource = new EnemyTurnStateChangedSource();
		_updateIndicator = new GameFieldUpdateIndicatorSource();
		_playerChangedSource = new PlayerChangedSource();
		_gamesPlayed = 1;
	}
	
	public GameStateSource GetGameStateSource()
	{
		return _stateSource;
	}
	
	public WinCounterSource GetWinCounterSource()
	{
		return _counterSource;
	}
	
	public GameFieldClearerSource GetGameFieldClearer()
	{
		return _fieldClearerSource;
	}
	
	public EnemyTurnStateChangedSource GetEnemyTurnStateChangedSource()
	{
		return _turnStateChangedSource;
	}
	
	public GameFieldUpdateIndicatorSource GetGameFieldUpdateIndicatorSource()
	{
		return _updateIndicator;
	}
	
	public PlayerChangedSource GetPlayerChangedSource()
	{
		return _playerChangedSource;
	}
	
	public int GetGamesPlayed()
	{
		return _gamesPlayed;
	}
	
	public Player GetPlayer()
	{
		return _player;
	}
	
	public Player GetPlayer1()
	{
		return _player1;
	}
	
	public Player GetPlayer2()
	{
		return _player2;
	}
	
	public Field GetField()
	{
		return _field;
	}
	
	public void Logik(Vector2i p)
	{
		if (!_field.IsFieldEmpty(p))
		{
			return;
		}
		_field.SetField(p, _player.GetSymbol());
		_updateIndicator.FireEvent();
		if (HasEnd())
		{
			GameStates state = GetGameState();
			if (state == GameStates.Gewonnen)
			{
				_player1.SetWinCounter(_player1.GetWinCounter()+1);
			}
			else if (state == GameStates.Verloren)
			{
				_player2.SetWinCounter(_player2.GetWinCounter()+1);
			}
			this._counterSource.FireEvent();
			_stateSource.FireEvent(state);
			return;
		}
		PlayerTausch();
		
		if (_player instanceof AIPlayer)
		{
			this._turnStateChangedSource.FireEvent(true);
			_enemyThread.SetEnemy(_player);
			_enemyThread.SetField(_field.Clone());
			Thread t = new Thread(_enemyThread);
			t.start();
		}
	}
	
	public void RestartGame()
	{
		_player = _player1;
		_field = new Field(_field.GetWidth(), _field.GetHeight());
		_fieldClearerSource.FireEvent();
		_gamesPlayed++;
	}
	
	public Boolean HasEnd()
	{
        return GetGameState() != GameStates.Laufend;
	}
	
	public GameStates GetGameState()
	{
		if (_player == null)
        {
            return GameStates.Laufend;
        }

		List<Player> players = new ArrayList<Player>();
		players.add(_player1);
		players.add(_player2);
		
        for (Player p : players)
        {
            if (WinConditionChecker.Check(p, _field))
            {
                if (_player.GetSymbol() == _player1.GetSymbol())
                {
                    return GameStates.Gewonnen;
                }
                else
                {
                    return GameStates.Verloren;
                }
            }
        }

        if (!_field.HasEmptyFields())
        {
            return GameStates.Unentschieden;
        }
        return GameStates.Laufend;
	}
	
	public void PlayerTausch()
	{
		_player  = _player == _player1 ? _player2 : _player1;
		_playerChangedSource.FireEvent();
	}

	@Override
	public void PlayerMoved(Vector2i p) {
		if (_player instanceof AIPlayer)
		{
			this._turnStateChangedSource.FireEvent(false);
		}
		Logik(p);
	}
}
