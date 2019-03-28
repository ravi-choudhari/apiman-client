package org.apiman.client.domain.permissions;

import java.util.Set;

import org.apiman.client.domain.currentuser.Permission;

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
public class UserPermissions {

    private String userId;
    private Set<Permission> permissions;
}
