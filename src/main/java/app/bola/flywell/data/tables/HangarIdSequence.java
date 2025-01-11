package app.bola.flywell.data.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name = "hangar_id_sequence")
public class HangarIdSequence {

    @Id
    private String locationCode;
    private int currentSequence;
}
