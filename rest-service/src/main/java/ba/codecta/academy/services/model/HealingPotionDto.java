package ba.codecta.academy.services.model;

public class HealingPotionDto extends ItemsDto{

    private Integer id;
   private Double healthAddition;
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
