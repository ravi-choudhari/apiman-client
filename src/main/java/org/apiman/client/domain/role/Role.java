package org.apiman.client.domain.role;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apiman.client.domain.permissions.PermissionType;

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
public class Role implements Serializable {

    private String id;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
    private Boolean autoGrant = Boolean.FALSE;
    private Set<PermissionType> permissions;
}
