package ba.codecta.academy.services.model;

public class PowerUpsDto extends ItemsDto{
    private Integer id;
    private Double powerValue;
    private String name;

    public Double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(Double powerValue) {
         this.powerValue = powerValue;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
