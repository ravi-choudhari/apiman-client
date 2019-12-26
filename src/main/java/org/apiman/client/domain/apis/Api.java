package org.apiman.client.domain.apis;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apiman.client.domain.organization.Organization;

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
public class Api implements Serializable {
    
	private Organization organization;
    private String id;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
    private Integer numPublished;
    private Set<ApiVersion> apiVersionSet;
}
