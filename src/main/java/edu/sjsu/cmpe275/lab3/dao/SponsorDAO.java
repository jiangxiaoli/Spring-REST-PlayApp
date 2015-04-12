package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Sponsor;

import java.util.List;

public interface SponsorDAO {
    public void insert(Sponsor sponsor);
    public Sponsor findBySponsorId(Long sponsorId);
    public void update(Sponsor sponsor);
    public boolean delete(long sponsorId);
    public List<Sponsor> allSponsors();
}
