package org.iskon.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="invite_code")
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteCode implements Serializable {
    @Id
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Date createdTime;

}
