package app.bola.flywell.data.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	
	ADMIN,
	CREW_MEMBER("pilot", "co-pilot", "flight attendant"),
	USER;
	
	Role(String... roles) {
	}
}
