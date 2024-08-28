package br.com.agendamento.api.exceptions.exceptionsHandler;

import lombok.Data;

@Data
public class StandardError {
    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
