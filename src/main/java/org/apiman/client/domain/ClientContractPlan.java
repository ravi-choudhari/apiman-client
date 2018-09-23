package org.apiman.client.domain;

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
public class ClientContractPlan {

	private int id;
	private String version;
	private String status;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
	private String lockedOn;
	private ClientPlan plan;
}
