package org.apiman.client.domain.currentuser;

import java.io.Serializable;

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
public class Permission implements Serializable {

    private PermissionType name;
    private String organizationId;
}
