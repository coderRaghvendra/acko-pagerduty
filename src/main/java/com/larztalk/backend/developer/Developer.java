package com.larztalk.backend.developer;

import com.larztalk.backend.BaseEntityAudit;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Entity
@Table(name = "developer")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Developer extends BaseEntityAudit {

    private long teamId;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String phoneNo = "";
}
