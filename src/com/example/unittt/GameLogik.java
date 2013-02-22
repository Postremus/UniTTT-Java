package com.example.unittt;
import java.util.List;
import java.util.ArrayList;

public class GameLogik implements IPlayerMovedListener {

	private Player _player;
	private Player _player1;
	private Player _player2;
	private Field _field;
	private WinConditionChecker _winChecker;
	private GameStateSource _stateSource;
	
	public GameLogik(Player player1, Player player2, Field field)
	{
		player1.GetMovedSource().Add(this);
		player2.GetMovedSource().Add(this);
		
		_player = player1;
		_player1 = player1;
		_player2 = player2;
		_field = field;
		_winChecker = new WinConditionChecker(field.GetWidth());
		_stateSource = new GameStateSource();
	}
	
	public GameStateSource GetGameStateSource()
	{
		return _stateSource;
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
	
	public void Logik(Vector2i p)
	{
		_field.SetField(p, _player.GetSymbol());
		if (HasEnd())
		{
			_stateSource.FireEvent(GetGameState());
		}
		PlayerTausch();
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
            if (_winChecker.Check(p, _field))
            {
                if (_player.GetSymbol() == p.GetSymbol())
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
	}

	@Override
	public void PlayerMoved(Vector2i p) {
		Logik(p);
	}
}
