package com.example.saudejausuarioservice.dtos;

import dtos.abstrato.Page;

import java.util.List;

public class UsuarioEmailDtoPage extends Page<String> {
    public UsuarioEmailDtoPage(int page, int size, List<String> content) {
        super(page, size, content);
    }
}
