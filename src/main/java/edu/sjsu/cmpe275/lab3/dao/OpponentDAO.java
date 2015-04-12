package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Player;

import java.util.List;
import java.util.Map;

public interface OpponentDAO {
    public void insert(Player player1, Player player2);
    public void delete(Player player1, Player player2);
    public void deleteP(Player player);
    public List<Player> findOpponentsByPlayer(Player player);
}
