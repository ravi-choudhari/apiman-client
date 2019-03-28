package org.apiman.client.domain.role;

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
public class GrantRoles {

    private String userId;
    private Set<String> roleIds;
}