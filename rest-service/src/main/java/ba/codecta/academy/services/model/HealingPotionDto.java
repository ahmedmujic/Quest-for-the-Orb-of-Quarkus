package ba.codecta.academy.services.model;

public class HealingPotionDto extends ItemsDto{
   private Double healthAddition;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHealthAddition() {
        return healthAddition;
    }

    public void setHealthAddition(Double healthAddition) {
        this.healthAddition = healthAddition;
    }
}
