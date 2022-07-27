import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Colegio {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();

    
    public void menu(){
        while(true){
        Scanner sc = new Scanner(System.in);
        Colegio ca = new Colegio();
        int id_codigo;
        String nombre;
        String direccion;
        int telefono;
        
        System.out.println("**********  Menú Colegio **********");
        System.out.println("* 1. Crear                        *");
        System.out.println("* 2. Listar                       *");
        System.out.println("* 3. Eliminar                     *");
        System.out.println("* 4. Modificar                    *");
        System.out.println("* 5. Salir                        *");
        System.out.println("***********************************");
        
        int respuesta=sc.nextInt();
        
        switch(respuesta){
            case 1: 
                System.out.println("Ingrese código"); 
                id_codigo = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                System.out.println("Ingrese dirección");
                direccion = sc.nextLine();
                System.out.println("Ingrese teléfono");
                telefono = sc.nextInt();
                ca.crear(id_codigo,nombre,direccion,telefono);    
                
                break;
                
            case 2: 
                listar();
                break;
                
            case 3: 
                System.out.println("Ingrese el código a eliminar");
                id_codigo = sc.nextInt();
                ca.eliminar(id_codigo);
                break; 
                
            case 4: 
                System.out.println("Ingrese el código a actualizar");
                id_codigo = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingrese el nombre a actualizar");
                nombre = sc.nextLine();
                System.out.println("Ingrese la dirección a actualizar");
                direccion = sc.nextLine();
                System.out.println("Ingrese el teléfono a actualizar");
                telefono = sc.nextInt();
                ca.actualizar(id_codigo, nombre, direccion, telefono);
                break;
                
            case 5: 
                System.exit(0);
                break;
                
            default:
                throw new AssertionError();                
        }        
    }
}
      
    public void crear(int id_codigo, String nombre, String direccion, int telefono){
       String sql = "insert into alumno(id_codigo, nombre, direccion, telefono) value(?,?,?,?)";
       try{
           con = conectar.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_codigo);
           ps.setString(2, nombre);
           ps.setString(3, direccion);
           ps.setInt(4, telefono);
           ps.executeUpdate();
           
       }catch(Exception e){      
    }
}
    
    public void listar(){
        String instruccion = "Select * from alumno";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getInt(4));
                System.out.println("___________________________________");
            }
        }catch(Exception e){        
    }        
}
    
    public void eliminar(int id_codigo){
        String sql = "DELETE FROM alumno WHERE id_codigo= ?";
        
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_codigo);
            ps.executeUpdate();
            
        }catch(Exception e){           
    }        
}
    
    public void actualizar(int id_codigo, String nombre, String direccion, int telefono){
        String sql = "UPDATE alumno SET nombre = ?, direccion=?, telefono=? where id_codigo=? ";
        try{
            con = conectar.Conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setInt(3, telefono);
            ps.setInt(4, id_codigo);
            ps.executeUpdate();
        }catch(Exception e){       
    }        
}  
    
    public static void main(String[] args) {
        Colegio col = new Colegio();
        col.menu();                     
    }
    
}
