package org.apiman.client.domain.clients;

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
public class ClientVersion {

    private Long id;
    private Client client;
    private ClientStatus status;
    private String version;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private Date publishedOn;
    private Date retiredOn;
    private String apikey;
}
