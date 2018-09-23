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
public class OrganizationMember {

	private String userName;
	private String email;
	private List<MemberRole> roles;
	private String userId;
	private String joinedOn;
}
