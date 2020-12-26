package ba.codecta.academy.services.model;

public class MonsterDto {
    private Integer id;
    private Double health;
    private Double damage;
    private String name;
    private Boolean alive;
    public MonsterDto(){}
    public MonsterDto(Double health, Double damage, String name) {
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }
}
