package ba.codecta.academy.repository.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(
                name = "ID"
        )
)
@Table(name = "HEALING_POTION")
public class HealingPotion extends Items{


    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name="HEALTH_ADDITION", nullable = false)
    private Double healthAddition;

    public HealingPotion(String name, Double healthAddition) {
        this.name = name;
        this.healthAddition = healthAddition;
    }

    public HealingPotion() {
    }

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
