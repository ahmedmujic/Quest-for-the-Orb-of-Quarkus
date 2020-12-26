package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Items;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class FightResponseDto {
    private String message;
    private Double currentHealth;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String killedMonsterName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WeaponDto currentWeapon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemsDto> collectedItems;
    private Boolean win;

    public FightResponseDto(){

    }

    public FightResponseDto(String message, Double currentHealth, String killedMonsterName, WeaponDto currentWeapon, List<ItemsDto> collectedItem, Boolean win) {
        this.message = message;
        this.currentHealth = currentHealth;
        this.killedMonsterName = killedMonsterName;
        this.currentWeapon = currentWeapon;
        this.collectedItems = collectedItem;
        this.win = win;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getKilledMonsterName() {
        return killedMonsterName;
    }

    public void setKilledMonsterName(String killedMonsterName) {
        this.killedMonsterName = killedMonsterName;
    }

    public WeaponDto getCurrentWeapon() {
        return currentWeapon;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public void setCurrentWeapon(WeaponDto currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public List<ItemsDto> getCollectedItems() {
        return collectedItems;
    }

    public void setCollectedItems(List<ItemsDto> collectedItems) {
        this.collectedItems = collectedItems;
    }
}
