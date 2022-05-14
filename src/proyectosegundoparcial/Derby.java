/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosegundoparcial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo
 */
public class Derby {
    private static String databaseURL = "jdbc:derby://localhost:1527/PrimeraDB;create=true;user=APP;password=APP";
    private static Connection connection = null; 
    public static void deleteRegisters() throws SQLException{
         connection = DriverManager.getConnection(databaseURL);
         
         PreparedStatement st2 = connection.prepareStatement("DELETE FROM APP.NTPINFO WHERE 1=1");
            int a2 = st2.executeUpdate();
            if (a2>0){
                System.out.println("----DELETE SUCCESSFUL-----");
          } 
                    
    }
    public static  ResultSet getSQL(String query) throws SQLException{
         
        connection = DriverManager.getConnection(databaseURL);
        String sql = query;
        Statement statement = connection.createStatement();
       return(statement.executeQuery(sql));
    }
    
    public static void insertRegister(int id, String name, int stratum, int precision, String origen, String destino, int offset, int delay){
        try {
            connection = DriverManager.getConnection(databaseURL);
            PreparedStatement st = connection.prepareStatement("INSERT INTO NTPinfo(id,nombre,stratum,precision,origen,destino,offset,delay)values(?,?,?,?,?,?,?,?)"); 
            st.setInt(1,id);
            st.setString(2, name);
            st.setInt(3,stratum);
            st.setInt(4,precision);
            st.setString(5, origen);
            st.setString(6, destino);
            st.setInt(7,offset);
            st.setInt(8,delay);  
            
            int a = st.executeUpdate();
            if (a>0){
                System.out.println("----INSERT SUCCESSFUL-----");
            } 

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoSegundoParcial.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al conectarse a DB: " + ex);
        }
        
    }
    
    public static void createTable() {
        try {
            connection = DriverManager.getConnection(databaseURL);
            PreparedStatement st = connection.prepareStatement("create table \"APP\".NTPINFO\n"
                    + "(\n"
                    + "	ID INTEGER,\n"
                    + "	NOMBRE VARCHAR(500),\n"
                    + "	STRATUM INTEGER,\n"
                    + "	PRECISION INTEGER,\n"
                    + "	ORIGEN VARCHAR(500),\n"
                    + "	DESTINO VARCHAR(500),\n"
                    + "	OFFSET INTEGER,\n"
                    + "	DELAY INTEGER\n"
                    + ")");

            int a = st.executeUpdate();
            if (a > 0) {
                System.out.println("----CREATE SUCCESSFUL-----");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoSegundoParcial.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al conectarse a DB: " + ex);
        }

    }
}
