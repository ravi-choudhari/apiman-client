package org.apiman.client.domain.summary;

import java.util.Date;

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
public class ApiSummary {

    private String organizationId;
    private String organizationName;
    private String id;
    private String name;
    private String description;
    private Date createdOn;
}
