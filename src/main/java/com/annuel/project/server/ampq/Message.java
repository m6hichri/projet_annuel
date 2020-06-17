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
public class Message implements Serializable {
    private String currency;
    private String method;
    private String intent;
    //private String description;

}