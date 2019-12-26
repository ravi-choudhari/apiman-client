package org.apiman.client.domain.summary;

import java.io.Serializable;

import org.apiman.client.domain.apis.UpdateApi;

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
public class ApiVersionEndpointSummary implements Serializable {

	private String managedEndpoint;
}
