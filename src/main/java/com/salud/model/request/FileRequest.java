package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileRequest {
    private String nombre;
    private byte[] archivo;

}
