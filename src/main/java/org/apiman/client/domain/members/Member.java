package org.apiman.client.domain.members;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apiman.client.domain.gateway.Gateway;

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
public class Member implements Serializable {

    private String userId;
    private String userName;
    private String email;
    private Date joinedOn;
    private List<MemberRole> roles;
}
