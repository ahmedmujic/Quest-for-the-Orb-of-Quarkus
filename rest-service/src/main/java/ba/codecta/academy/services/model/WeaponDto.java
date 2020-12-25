package ba.codecta.academy.services.model;



public class WeaponDto {
   private Integer id;
    private String weaponName;
    private Integer damage;
    private Integer weaponHealth;


    public Integer getWeaponHealth() {
        return weaponHealth;
    }

    public void setWeaponHealth(Integer weaponHealth) {
        this.weaponHealth = weaponHealth;
    }

    public WeaponDto(String weaponName, Integer damage) {
        this.weaponName = weaponName;
        this.damage = damage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}
