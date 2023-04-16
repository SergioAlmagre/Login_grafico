package Conexiones
import com.example.login_grafico.Usuario
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

object Conexion {
    // ********************* Atributos *************************
    var conexion: Connection? = null

    // Atributo a través del cual hacemos la conexión física.
    var sentenciaSQL: Statement? = null

    // Atributo que nos permite ejecutar una sentencia SQL
    var registros: ResultSet? = null


    // ---------------------CONEXIONADO-----------------------------------
    fun abrirConexion(): Int {
        var cod = 0
        try {
            // Cargar el driver/controlador JDBC de MySql
            val controlador = "com.mysql.cj.jdbc.Driver"
            val URL_BD = "jdbc:mysql://" + Constantes.servidor + ":" + Constantes.puerto + "/" + Constantes.bbdd
            Class.forName(controlador)

            // Realizamos la conexión a una BD con un usuario y una clave.
            conexion = DriverManager.getConnection(URL_BD, Constantes.usuario, Constantes.passwd)
            sentenciaSQL = conexion!!.createStatement()
//            println("Conexion realizada con éxito")
        } catch (e: Exception) {
            println("Exception: " + e.message)
            cod = -1
        }
        return cod
    }

    fun cerrarConexion(): Int {
        var cod = 0
        try {
            conexion!!.close()
//            println("Desconectado de la Base de Datos") // Opcional para seguridad
        } catch (ex: Exception) {
            println("Error al cerrar la conexión.")
            cod = -1
        }
        return cod
    }

    fun insertarUsuario(usu:Usuario): String {
        var mensaje:String = ""
        try {
        var sentencia = "INSERT INTO usuario values (" +
                " '${usu.nombre}' ," +
                " '${usu.apellido1}' ," +
                " '${usu.apellido2}' ," +
                " '${usu.dni}' ," +
                " '${usu.telefono}' ," +
                " '${usu.clave}' ) "

        Conexion.abrirConexion()
        sentenciaSQL!!.executeUpdate(sentencia)
        Conexion.cerrarConexion()
        }catch (ex:SQLException){
            println(ex)
            mensaje = ex.message.toString()
        }
      return mensaje
    }


    fun obtenerUsuario(dni:String, clave:String):Usuario?{
        var mensaje:String = ""
        var u: Usuario? = null
        var registros: ResultSet? = null

        try {
        abrirConexion()
        var sentencia = "SELECT * FROM ${Constantes.tablaUsuario} WHERE UPPER (dni) LIKE '$dni' AND UPPER (clave) like '$clave'"
        registros = sentenciaSQL!!.executeQuery(sentencia)
            if (registros!!.next()){
                u = Usuario(
                    registros!!.getString("nombre"),
                    registros!!.getString("apellido1"),
                    registros!!.getString("apellido2"),
                    registros!!.getString("dni"),
                    registros!!.getString("telefono"),
                    registros!!.getString("clave")
                )
            }
        }catch (ex:SQLException){
            println(ex)
            mensaje = ex.message.toString()
        }
        cerrarConexion()

        return u
    }





}
