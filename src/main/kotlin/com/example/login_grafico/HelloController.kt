package com.example.login_grafico

import Conexiones.Conexion
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField

class HelloController {


    @FXML
    private lateinit var apellido1: TextField

    @FXML
    private lateinit var apellido2: TextField

    @FXML
    private lateinit var claveEntrar: TextField

    @FXML
    private lateinit var claveRegistro: TextField

    @FXML
    private lateinit var display: TextField

    @FXML
    private lateinit var dniEntrar: TextField

    @FXML
    private lateinit var dniRegistro: TextField

    @FXML
    private lateinit var nombre: TextField

    @FXML
    private lateinit var telefono: TextField


    @FXML
    fun botonEntrar(event: ActionEvent) {
        var usu = Conexion.obtenerUsuario(dniEntrar.text,claveEntrar.text)
        if (usu == null){
            display.text = "Fallo en el DNI o clave"
        }else{
            display.text = "Bievenid@ ${usu.nombre} ${usu.apellido1} ${usu.apellido2}"
        }

    }

    @FXML
    fun botonRegistrarse(event: ActionEvent) {
        var usu:Usuario = Usuario(nombre.text,apellido1.text,apellido2.text,dniRegistro.text,telefono.text,claveRegistro.text)
        if (Conexion.insertarUsuario(usu) == ""){
            display.text = "Registrado con éxito"
        }else{
            display.text = "Fallo al insertar usuario"
        }

    }



}