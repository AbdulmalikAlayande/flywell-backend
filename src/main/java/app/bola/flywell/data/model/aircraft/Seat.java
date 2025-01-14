package app.bola.flywell.data.model.aircraft;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.SeatClass;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Seat extends FlyWellModel {

    private int rowPosition;
    private int seatNumber;

    @Enumerated(value = EnumType.STRING)
    private SeatClass seatClass;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("publicId", getPublicId())
                .add("rowPosition", rowPosition)
                .add("seatNumber", seatNumber)
                .toString();
    }
}
