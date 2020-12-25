package ba.codecta.academy.services.model;

public class MonsterDto {
    private Integer id;
    private Integer health;
    private Integer damage;
    private String name;

    public MonsterDto(){}
    public MonsterDto(Integer health, Integer damage, String name) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}
