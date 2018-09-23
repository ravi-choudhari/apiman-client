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
public class ClientContract {

	private int id;
	private String createdOn;
	private String createdBy;
	private CreateApiVersion api;
	private Client client;
	private ClientContractPlan plan;
}
