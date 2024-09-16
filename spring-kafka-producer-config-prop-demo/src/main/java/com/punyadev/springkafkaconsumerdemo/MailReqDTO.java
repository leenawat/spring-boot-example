package com.punyadev.springkafkaconsumerdemo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7929415447487091922L;

    private String jobName;
    private String environment;
    private String dateTime;
    private String message;
}
