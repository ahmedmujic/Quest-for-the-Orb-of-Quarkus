package ba.codecta.academy.services.model;

public class FleeResponseDto {
    private Integer dungeonId;
    private PlayerDto playerDto;
    private Double lostScore;
    private Double lostHealth;

    public FleeResponseDto() {
    }

    public FleeResponseDto(Integer dungeonId, PlayerDto playerDto, Double lostScore, Double lostHealth) {
        this.dungeonId = dungeonId;
        this.playerDto = playerDto;
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
        return playerDto;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
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
