package com.larztalk.backend.test;

import com.larztalk.androidsdk.constant.Gender;
import com.larztalk.backend.BaseEntityAudit;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 30/07/20.
 */
@Entity
@Table(name = "test")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Test extends BaseEntityAudit {

    @Builder.Default
    private String fname = "";

    @Builder.Default
    private String lname = "";
}
