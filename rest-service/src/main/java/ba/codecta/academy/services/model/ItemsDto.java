package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.PowerUps;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemsDto {
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HealingPotionDto healingPotion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PowerUpsDto powerUps;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public HealingPotionDto getHealingPotion() {
        return healingPotion;
    }

    public void setHealingPotion(HealingPotionDto healingPotion) {
        this.healingPotion = healingPotion;
    }

    public PowerUpsDto getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(PowerUpsDto powerUps) {
        this.powerUps = powerUps;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
