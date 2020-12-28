package ba.codecta.academy.services.model;

public class FleeResponseDto {
    private Integer dungeonId;
    private PlayerDto player;
    private Double lostScore;
    private Double lostHealth;

    public FleeResponseDto() {
    }

    public FleeResponseDto(Integer dungeonId, PlayerDto playerDto, Double lostScore, Double lostHealth) {
        this.dungeonId = dungeonId;
        this.player = playerDto;
        this.lostScore = lostScore;
        this.lostHealth = lostHealth;
    }

    public Integer getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Integer dungeonId) {
        this.dungeonId = dungeonId;
    }

    public PlayerDto getPlayerDto() {
        return player;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.player = playerDto;
    }

    public Double getLostScore() {
        return lostScore;
    }

    public void setLostScore(Double lostScore) {
        this.lostScore = lostScore;
    }

    public Double getLostHealth() {
        return lostHealth;
    }

    public void setLostHealth(Double lostHealth) {
        this.lostHealth = lostHealth;
    }
}
