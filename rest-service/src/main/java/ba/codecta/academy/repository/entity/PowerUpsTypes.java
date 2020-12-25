package ba.codecta.academy.repository.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(
                name = "ID"
        )
)
@Table(name = "POWER_UPS_TYPES")
public class PowerUpsTypes extends PowerUps{
    @Column(name = "TYPE", nullable = false)
    private String powerName;

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }
}
