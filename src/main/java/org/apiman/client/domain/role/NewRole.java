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
public class NewRole implements Serializable {

    private String name;
    private String description;
    private Boolean autoGrant;
    private Set<PermissionType> permissions;
}
