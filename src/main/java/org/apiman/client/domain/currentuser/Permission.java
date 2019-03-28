package org.apiman.client.domain.currentuser;

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
public class Permission {

    private PermissionType name;
    private String organizationId;
}
