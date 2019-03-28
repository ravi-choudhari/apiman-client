package org.apiman.client.domain.gateway;

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
public class Gateway {

    private String id;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
}
