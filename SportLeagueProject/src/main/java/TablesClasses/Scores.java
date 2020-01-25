package TablesClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Scores {
    @Id
    private int id;
    @Column(name = "idMeczu", nullable = false)
    private int matchId;
    @Column(name = "idZawodnika", nullable = false)
    private int playerId;
    @Column(name = "punktyDruzynyPierwszejPoPunkcie", nullable = false)
    private int teamOneScoreAfterPoint;
    @Column(name = "punktyDruzynyDrugiejPoPunkcie", nullable = false)
    private int teamTwoScoreAfterPoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamTwoScoreAfterPoint() {
        return teamTwoScoreAfterPoint;
    }

    public void setTeamTwoScoreAfterPoint(int teamTwoScoreAfterPoint) {
        this.teamTwoScoreAfterPoint = teamTwoScoreAfterPoint;
    }

    public int getTeamOneScoreAfterPoint() {
        return teamOneScoreAfterPoint;
    }

    public void setTeamOneScoreAfterPoint(int teamOneScoreAfterPoint) {
        this.teamOneScoreAfterPoint = teamOneScoreAfterPoint;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
