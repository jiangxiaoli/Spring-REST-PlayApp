package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Player;

import java.util.List;

public interface PlayerDAO {
    public boolean insert(Player player);
    public Player findByPlayerId(long playerId);
    public void update(Player player);
    public boolean delete(long playerId);
    public List<Player> allPlayers();
}
