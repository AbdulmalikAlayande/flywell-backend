package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse extends BaseResponse {

	Long frequentFlyerNumber;
	UserBioDataResponse userBioData;

}
