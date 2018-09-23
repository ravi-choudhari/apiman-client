package org.apiman.client.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

	private String fullName;
	private String username;
	private String email;
	private boolean admin;
	private String joinedOn;
	private List<Permission> permissions;
}
