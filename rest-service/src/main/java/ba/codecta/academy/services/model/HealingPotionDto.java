package ba.codecta.academy.services.model;

public class HealingPotionDto extends ItemsDto{
   private Double healthAddition;

    public Double getHealthAddition() {
        return healthAddition;
    }

    public void setHealthAddition(Double healthAddition) {
        this.healthAddition = healthAddition;
    }
}
