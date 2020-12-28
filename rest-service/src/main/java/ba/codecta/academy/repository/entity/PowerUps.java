package ba.codecta.academy.repository.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(
                name = "ID"
        )
)
@Table(name = "POWER_UPS")
@Inheritance(strategy = InheritanceType.JOINED)
public class PowerUps extends  Items{

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name="POWER_VALUE", nullable = false)
    private Double powerValue;

    public PowerUps(String name, Double healthAddition) {
        this.name = name;
        this.powerValue = healthAddition;
    }

    public PowerUps() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(Double powerValue) {
        this.powerValue = powerValue;
    }
}
