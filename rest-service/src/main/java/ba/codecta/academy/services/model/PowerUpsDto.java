package ba.codecta.academy.services.model;

public class PowerUpsDto extends ItemsDto{
    private Double powerValue;
    private String name;

    public Double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(Double powerValue) {
         this.powerValue = powerValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
