package com.annuel.project.server.ampq;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Match implements Serializable {

    private String homeName;
    private int homePoints;
    private String visitorName;
    private int visitorPoints;
}
