package com.plexus.apisuperheroes.controller;

import com.plexus.apisuperheroes.dto.UsuarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API de Usuarios")
public interface UsuariosController {

  @ApiOperation(value = "Obtener token para un usuario", notes = "Devuelve un token para operaciones de modificación de superheroes")
  UsuarioDTO login(String usuario);

}
