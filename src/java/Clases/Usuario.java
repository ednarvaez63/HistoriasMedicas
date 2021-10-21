/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import ClasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class Usuario {
    private String id;
    private String documento;
    private String nombre;
    private String apellido;
    private String edad;
    private String usuario;
    private String clave;
    private String firma;
    private String idTipoUsuario;
    private String celular;
    private String direccion;
    private String idEspecialidad;

    public Usuario() {
    }

    public Usuario(String id, String documento, String nombre, String apellido, String especialidad, String edad, String usuario, String clave, String firma, String idTipoUsuario, String celular, String direccion, String idEspecialidad) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.nombre = apellido;
        this.edad = edad;
        this.usuario = usuario;
        this.clave = clave;
        this.firma = firma;
        this.idTipoUsuario = idTipoUsuario;
        this.celular = celular;
        this.direccion = direccion;
        this.idEspecialidad = idEspecialidad;
    }

    public Usuario(String id) {
        String cadenaSQL="select id, documento, nombre, apellido, edad, usuario, clave, firma, idTipoUsuario, celular, direccion, idEspecialidad from usuario where id='"+id+"'";
        try {
            
            ResultSet resultado=ConectorBD.consultar(cadenaSQL);
            if (resultado.next()){
                this.id=id;
                this.documento=resultado.getString("documento");
                this.nombre=resultado.getString("nombre");
                this.apellido=resultado.getString("apellido");
                this.edad=resultado.getString("edad");
                this.usuario=resultado.getString("usuario");
                this.clave=resultado.getString("clave");
                this.firma=resultado.getString("firma");
                this.idTipoUsuario=resultado.getString("idTipoUsuario");
                this.celular=resultado.getString("celular");
                this.direccion=resultado.getString("direccion");
                this.idEspecialidad=resultado.getString("idEspecialidad");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al consultar usuario"+cadenaSQL+"\n"+ex.getMessage());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getTipo() {
        return idTipoUsuario;
    }

    public void setTipo(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }
    
    public TipoUsuario getTipoUsuario(){
         return new TipoUsuario(idTipoUsuario);
    }

    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }
    
    public Especialidad getTipoEspecialidad(){
         return new Especialidad(idEspecialidad);
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public void guardar(){
        //String cadena = tipo.substring(0,0);
        String cadenaSQL="insert into usuario (documento, nombre, apellido, edad, usuario, clave, firma, idTipoUsuario, celular, direccion, idEspecialidad) values ('"+ documento 
                +"','"+ nombre +"','"+ apellido +"','"+ edad +"','"+ usuario +"',"+ "md5('"+ clave +"'),'"+firma+"','"+ idTipoUsuario +"','"+ celular +"','"+ direccion +"','"+ idEspecialidad +"')";
        ConectorBD.ejecutarQuery(cadenaSQL);
    }
    public void modificar(/*String identificacionAnterior*/){
        if(clave.length()==32)clave="'"+clave+"'";
        else clave="md5 ('"+clave+"')";
        String cadenaSQL="update usuario set id='"+id+"', documento='"+documento+"',"+ " nombre='"+nombre+ " apellido='"+apellido+ " edad='"+edad
                + " usuario='"+usuario+"', clave="+clave+", firma='"+firma+", idTipoUsuario='"+idTipoUsuario+", celular='"+celular+", direccion='"+direccion+", idEspecialidad='"+idEspecialidad+"' where id='"+id+"'";
        ConectorBD.ejecutarQuery(cadenaSQL);
    }
    public void eliminar(){
        String cadenaSQL="delete from usuario where id='"+ id +"'";
        ConectorBD.ejecutarQuery(cadenaSQL);
    }
    public static ResultSet getLista(String filtro){
        if(filtro==null)filtro="";
        else filtro="order"+filtro;
        String cadenaSQL=" select id, documento, nombre, apellido, edad, usuario, clave, firma, idTipoUsuario, celular, direccion, idEspecialidad from usuario "+ filtro +" by id";
        return ConectorBD.consultar(cadenaSQL);
    }
    
    public static ArrayList<Usuario> getListaEnObjetos(String filtro){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        ResultSet resultado=Usuario.getLista(filtro);
        try {
            while(resultado.next()){
                Usuario usuario=new Usuario();
                usuario.setId(resultado.getString("id"));
                usuario.setDocumento(resultado.getString("documento"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setEdad(resultado.getString("edad"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setFirma(resultado.getString("firma"));
                usuario.setIdTipoUsuario(resultado.getString("idTipoUsuario"));
                usuario.setCelular(resultado.getString("celular"));
                usuario.setDireccion(resultado.getString("direccion"));
                usuario.setIdEspecialidad(resultado.getString("idEspecialidad"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No es posible obtener la lista de usuario Error: "+ex.getMessage());
        }
        return lista;
    }
    
    public static Usuario validar(String usuario, String clave){
        Usuario usuario1=null;
        String cadenaSQL="select id, documento, nombre, apellido, edad, usuario, clave, firma, idTipoUsuario, celular, direccion, idEspecialidad from usuario where usuario='"+ usuario + "' and clave=md5('"+ clave +"')";
        try {
            ResultSet resultado=ConectorBD.consultar(cadenaSQL);
            if(resultado.next()){
                usuario1=new Usuario();
                usuario1.id=resultado.getString("id");
                usuario1.documento=resultado.getString("documento");
                usuario1.nombre=resultado.getString("nombre");
                usuario1.apellido=resultado.getString("apellido");
                usuario1.edad=resultado.getString("edad");
                usuario1.usuario=resultado.getString("usuario");
                usuario1.clave=resultado.getString("clave");
                usuario1.firma=resultado.getString("firma");
                usuario1.idTipoUsuario=resultado.getString("idTipoUsuario");
                usuario1.celular=resultado.getString("celular");
                usuario1.direccion=resultado.getString("direccion");
                usuario1.idEspecialidad=resultado.getString("idEspecialidad");
                
            }
        } catch (Exception e) {
            System.out.println("Error al validar usuario "+cadenaSQL+"\n"+e.getMessage());
        }
        return usuario1;
    }
    
}
