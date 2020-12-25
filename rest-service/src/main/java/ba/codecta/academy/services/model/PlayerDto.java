package ba.codecta.academy.services.model;

public class PlayerDto {
    private Integer id;
    private Integer healingPoting = 100;
    private Integer health = 100;
    private Integer score = 0;
    private String name;
    private DungeonDto dungeonDto;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }


    public DungeonDto getDungeonDto() {
        return dungeonDto;
    }

    public void setDungeonDto(DungeonDto dungeonDto) {
        this.dungeonDto = dungeonDto;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Integer healthPoints) {
        this.healingPoting = healthPoints;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
