package proyectomundial.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO w_sanchez1.seleccion (nombres, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT nombres, continente, dt, nacionalidad FROM w_sanchez1.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSeleccionesBusqueda(String nombreSeleccion) {
        System.out.println("LLEGAMOS HASTA GETSELECCIONESBUSQUEDA");
        String sql = "SELECT nombres, continente, dt, nacionalidad FROM w_sanchez1.seleccion WHERE nombres LIKE ? OR continente LIKE ? OR dt LIKE ? OR nacionalidad LIKE ?";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return selecciones;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreSeleccion + "%");
            stmt.setString(2, "%" + nombreSeleccion + "%");
            stmt.setString(3, "%" + nombreSeleccion + "%");
            stmt.setString(4, "%" + nombreSeleccion + "%");
            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public int Cantidad_de_selecciones() {
        int total_partidos = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(*) AS total_selecciones FROM w_sanchez1.seleccion s");
            if (result.next()) {
                total_partidos = result.getInt("total_selecciones");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }
        return total_partidos;

    }

    public int nacionalidades_dt() {
        int nacionalidad_dt = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(DISTINCT nacionalidad) AS cantidad_nacionalidades FROM w_sanchez1.seleccion s ;");
            if (result.next()) {
                nacionalidad_dt = result.getInt("cantidad_nacionalidades");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }
        return nacionalidad_dt;

    }

    public List selecciones_por_continente() {

        String sql = "SELECT continente, COUNT(*) AS cantidad_selecciones  FROM w_sanchez1.seleccion s  GROUP BY continente ";

        List seleccion = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String continente = result.getString("continente");
                    int cantidadSelecciones = result.getInt("cantidad_selecciones");
                    seleccion.add(continente);
                    seleccion.add(cantidadSelecciones);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return seleccion;
    }

    public List Ranking_nacionalidad() {

        String sql = "SELECT nacionalidad, COUNT(*) AS cantidad\n"
                + "FROM w_sanchez1.seleccion s \n"
                + "GROUP BY nacionalidad\n"
                + "ORDER BY cantidad DESC; ";

        List seleccion = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String nacionalidad = result.getString("nacionalidad");
                    int cantidadSeleccionesDT = result.getInt("cantidad");
                    seleccion.add(nacionalidad);
                    seleccion.add(cantidadSeleccionesDT);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return seleccion;
    }

}
