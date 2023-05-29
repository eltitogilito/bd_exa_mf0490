package servlet;

import controlador.EjecutaSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Conector")
public class Conector extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //getParameter recibe un parámetro
        String usuario = request.getParameter("usu");
        String password = request.getParameter("pas");

        PrintWriter out = response.getWriter();
     

        ResultSet rs = EjecutaSQL.devuelveRS("Select * from seguridad "
                + "where usuario='" + usuario + "' and contraseña='" + password + "';", "root", "1234",
                "jdbc:mysql://localhost:3306/bd_ex_mf0490");

        try {
            rs.next();
            if (rs.getRow() == 1) {
                out.println("Bienvenido a la pagina de inicio");
            } else {
                out.println("Usuario o Password incorrecto");
            }

           
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
