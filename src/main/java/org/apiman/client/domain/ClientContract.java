package org.apiman.client.domain;

import java.io.Serializable;

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
public class ClientContract implements Serializable {

	private int id;
	private String createdOn;
	private String createdBy;
	private CreateApiVersion api;
	private Client client;
	private ClientContractPlan plan;
}
