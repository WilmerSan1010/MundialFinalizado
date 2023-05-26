/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyectomundial.model.Resultados;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author LAB205PC16
 */
public class ResulltadosDAO {

    public ResulltadosDAO() {
        BasedeDatos.conectar();
    }

    public static boolean registrarResultados(Resultados resultados) {
        System.out.println("registrar resultados");
        String sql = "INSERT INTO w_sanchez1.partidos (grupo, local,visitante,continente_local,continente_visitante,goles_local,goles_visitante) values("
                + "'" + resultados.getGrupo() + "', "
                + "'" + resultados.getLocal() + "', "
                + "'" + resultados.getVisitante() + "', "
                + "'" + resultados.getContinente_local() + "',"
                + "'" + resultados.getContinente_visitante() + "',"
                + "'" + resultados.getGoles_locales() + "',"
                + "'" + resultados.getGoles_visitante() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultados> getResultados() {
        System.out.println("getresultados");
        String sql = "SELECT grupo,local,visitante,continente_local,continente_visitante,goles_local,goles_visitante FROM  w_sanchez1.partidos ";
        List<Resultados> resultados = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public List<Resultados> getresultadosbusquedas(String nombreS) {

        String sql = "SELECT grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM w_sanchez1.partidos WHERE local LIKE ? OR grupo LIKE ? OR visitante LIKE ? OR continente_local LIKE ? OR continente_visitante LIKE ?";
        List<Resultados> resultados = new ArrayList<Resultados>();
        try {
            if (BasedeDatos.conexion == null) {
                System.out.println("No hay conexión a la base de datos");
                return resultados;
            }
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreS + "%");
            stmt.setString(2, "%" + nombreS + "%");
            stmt.setString(3, "%" + nombreS + "%");
            stmt.setString(4, "%" + nombreS + "%");
            stmt.setString(5, "%" + nombreS + "%");

            ResultSet result = stmt.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public String[][] getResultadosMatriz() {

        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultados();

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_locales();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;

    }

    public List partido_mas_goles() {

        String sql = "SELECT\n"
                + "  CONCAT(mas_goles.local, ' vs. ', mas_goles.visitante) AS partido_mas_goles,\n"
                + "  mas_goles.goles_totales AS goles_mas,\n"
                + "  CONCAT(menos_goles.local, ' vs. ', menos_goles.visitante) AS partido_menos_goles,\n"
                + "  menos_goles.goles_totales AS goles_menos\n"
                + "FROM\n"
                + "  (SELECT\n"
                + "    local,\n"
                + "    visitante,\n"
                + "    goles_local + goles_visitante AS goles_totales\n"
                + "  FROM\n"
                + "    w_sanchez1.partidos\n"
                + "  ORDER BY\n"
                + "    goles_totales DESC\n"
                + "  LIMIT 1) AS mas_goles,\n"
                + "  (SELECT\n"
                + "    local,\n"
                + "    visitante,\n"
                + "    goles_local + goles_visitante AS goles_totales\n"
                + "  FROM\n"
                + "    w_sanchez1.partidos\n"
                + "  ORDER BY\n"
                + "    goles_totales ASC\n"
                + "  LIMIT 1) AS menos_goles";
        List resultados = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String partidoMasGoles = result.getString("partido_mas_goles");
                    int golesMas = result.getInt("goles_mas");
                    String partidoMenosGoles = result.getString("partido_menos_goles");
                    int golesMenos = result.getInt("goles_menos");
                    resultados.add(partidoMasGoles);
                    resultados.add(golesMas);
                    resultados.add(partidoMenosGoles);
                    resultados.add(golesMenos);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public List Continente_mas_goles() {

        String sql = "SELECT continente, total_goles\n"
                + "FROM (\n"
                + "    SELECT continente, SUM(total_goles) AS total_goles\n"
                + "    FROM (\n"
                + "        SELECT continente_local AS continente, SUM(goles_local) AS total_goles\n"
                + "        FROM w_sanchez1.partidos p \n"
                + "        GROUP BY continente_local\n"
                + "        UNION ALL\n"
                + "        SELECT continente_visitante AS continente, SUM(goles_visitante) AS total_goles\n"
                + "        FROM w_sanchez1.partidos p3 \n"
                + "        GROUP BY continente_visitante\n"
                + "    ) AS subquery\n"
                + "    GROUP BY continente\n"
                + "    ORDER BY total_goles DESC\n"
                + "    LIMIT 1\n"
                + ") AS max_goles\n"
                + "UNION ALL\n"
                + "SELECT continente, total_goles\n"
                + "FROM (\n"
                + "    SELECT continente, SUM(total_goles) AS total_goles\n"
                + "    FROM (\n"
                + "        SELECT continente_local AS continente, SUM(goles_local) AS total_goles\n"
                + "        FROM w_sanchez1.partidos p2 \n"
                + "        GROUP BY continente_local\n"
                + "        UNION ALL\n"
                + "        SELECT continente_visitante AS continente, SUM(goles_visitante) AS total_goles\n"
                + "        FROM w_sanchez1.partidos p4 \n"
                + "        GROUP BY continente_visitante\n"
                + "    ) AS subquery\n"
                + "    GROUP BY continente\n"
                + "    ORDER BY total_goles ASC\n"
                + "    \n"
                + ") AS min_goles;";
        List resultadosContinente = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String partidoContinente = result.getString("continente");
                    int totalGoles = result.getInt("total_goles");

                    resultadosContinente.add(partidoContinente);
                    resultadosContinente.add(totalGoles);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultadosContinente;
    }

    public List seleccion_mas_goles_menos_goles() {

        String sql = "SELECT equipo, total_goles\n"
                + "FROM (\n"
                + "    SELECT equipo, SUM(total_goles) AS total_goles\n"
                + "    FROM (\n"
                + "        SELECT local AS equipo, SUM(goles_local) AS total_goles\n"
                + "        FROM w_sanchez1.partidos\n"
                + "        GROUP BY local\n"
                + "        UNION ALL\n"
                + "        SELECT visitante AS equipo, SUM(goles_visitante) AS total_goles\n"
                + "        FROM w_sanchez1.partidos\n"
                + "        GROUP BY visitante\n"
                + "    ) AS subquery\n"
                + "    GROUP BY equipo\n"
                + ") AS subquery2\n"
                + "WHERE total_goles = (\n"
                + "    SELECT MAX(total_goles) AS max_goles\n"
                + "    FROM (\n"
                + "        SELECT equipo, SUM(total_goles) AS total_goles\n"
                + "        FROM (\n"
                + "            SELECT local AS equipo, SUM(goles_local) AS total_goles\n"
                + "            FROM w_sanchez1.partidos\n"
                + "            GROUP BY local\n"
                + "            UNION ALL\n"
                + "            SELECT visitante AS equipo, SUM(goles_visitante) AS total_goles\n"
                + "            FROM w_sanchez1.partidos\n"
                + "            GROUP BY visitante\n"
                + "        ) AS subquery\n"
                + "        GROUP BY equipo\n"
                + "    ) AS subquery3\n"
                + ")\n"
                + "OR total_goles = (\n"
                + "    SELECT MIN(total_goles) AS min_goles\n"
                + "    FROM (\n"
                + "        SELECT equipo, SUM(total_goles) AS total_goles\n"
                + "        FROM (\n"
                + "            select local AS equipo, SUM(goles_local) AS total_goles\n"
                + "            FROM w_sanchez1.partidos\n"
                + "            GROUP BY local\n"
                + "            UNION ALL\n"
                + "            SELECT visitante AS equipo, SUM(goles_visitante) AS total_goles\n"
                + "            FROM w_sanchez1.partidos\n"
                + "            GROUP BY visitante\n"
                + "        ) AS subquery\n"
                + "        GROUP BY equipo\n"
                + "    ) AS subquery4\n"
                + ");";
        List seleccionMX = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String seleccionMAXMIN = result.getString("equipo");
                    int seleccionMaxmin = result.getInt("total_goles");

                    seleccionMX.add(seleccionMAXMIN);
                    seleccionMX.add(seleccionMaxmin);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return seleccionMX;
    }

    public DefaultTableModel obtenerModeloTablaClasificados() {
        String sql = "SELECT grupo, equipo, puntos\n"
                + "FROM (\n"
                + "    SELECT grupo, equipo, puntos,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY grupo ORDER BY puntos DESC) AS posicion\n"
                + "    FROM (\n"
                + "        SELECT grupo, equipo, SUM(puntos) AS puntos\n"
                + "        FROM (\n"
                + "            SELECT grupo, local AS equipo, \n"
                + "                   CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n"
                + "            FROM w_sanchez1.partidos p \n"
                + "            GROUP BY grupo, local, goles_local, goles_visitante\n"
                + "            UNION ALL\n"
                + "            SELECT grupo, visitante AS equipo, \n"
                + "                   CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n"
                + "            FROM w_sanchez1.partidos p \n"
                + "            GROUP BY grupo, visitante, goles_local, goles_visitante\n"
                + "        ) AS subquery\n"
                + "        GROUP BY grupo, equipo\n"
                + "    ) AS subquery2\n"
                + ") AS subquery3\n"
                + "WHERE posicion <= 2\n"
                + "ORDER BY grupo, posicion;";

        DefaultTableModel modeloTablaClasificados = new DefaultTableModel();
        modeloTablaClasificados.addColumn("Grupo");
        modeloTablaClasificados.addColumn("Equipo");
        modeloTablaClasificados.addColumn("Puntos");

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String grupo = result.getString("grupo");
                    String equipo = result.getString("equipo");
                    int puntos = result.getInt("puntos");
                    modeloTablaClasificados.addRow(new Object[]{grupo, equipo, puntos});
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return modeloTablaClasificados;
    }

    public List seleccion_mas_puntos_menos_puntos() {

        String sql = "SELECT equipo, total_puntos\n"
                + "FROM (\n"
                + "    SELECT equipo, SUM(total_puntos) AS total_puntos\n"
                + "    FROM (\n"
                + "        SELECT local AS equipo, \n"
                + "               SUM(CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "        FROM w_sanchez1.partidos p5 \n"
                + "        GROUP BY local\n"
                + "        UNION ALL\n"
                + "        SELECT visitante AS equipo, \n"
                + "               SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "        FROM w_sanchez1.partidos p4 \n"
                + "        GROUP BY visitante\n"
                + "    ) AS subquery\n"
                + "    GROUP BY equipo\n"
                + ") AS subquery2\n"
                + "WHERE total_puntos = (\n"
                + "    SELECT MAX(total_puntos) AS max_puntos\n"
                + "    FROM (\n"
                + "        SELECT equipo, SUM(total_puntos) AS total_puntos\n"
                + "        FROM (\n"
                + "            SELECT local AS equipo, \n"
                + "                   SUM(CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "            FROM w_sanchez1.partidos p3 \n"
                + "            GROUP BY local\n"
                + "            UNION ALL\n"
                + "            SELECT visitante AS equipo, \n"
                + "                   SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "            FROM w_sanchez1.partidos p2 \n"
                + "            GROUP BY visitante\n"
                + "        ) AS subquery3\n"
                + "        GROUP BY equipo\n"
                + "    ) AS subquery4\n"
                + ")\n"
                + "OR total_puntos = (\n"
                + "    SELECT MIN(total_puntos) AS min_puntos\n"
                + "    FROM (\n"
                + "        SELECT equipo, SUM(total_puntos) AS total_puntos\n"
                + "        FROM (\n"
                + "            SELECT local AS equipo, \n"
                + "                   SUM(CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "            FROM w_sanchez1.partidos p6 \n"
                + "            GROUP BY local\n"
                + "            UNION ALL\n"
                + "            SELECT visitante AS equipo, \n"
                + "                   SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos\n"
                + "            FROM w_sanchez1.partidos p \n"
                + "            GROUP BY visitante\n"
                + "        ) AS subquery5\n"
                + "        GROUP BY equipo\n"
                + "    ) AS subquery6\n"
                + ")\n"
                + "ORDER BY total_puntos DESC\n"
                + "LIMIT 4; ";
        List seleccionMaxPuntosMINpuntos = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    String seleccionMMIN = result.getString("equipo");
                    int totalPuntos = result.getInt("total_puntos");

                    seleccionMaxPuntosMINpuntos.add(seleccionMMIN);
                    seleccionMaxPuntosMINpuntos.add(totalPuntos);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return seleccionMaxPuntosMINpuntos;

    }

    public int Cantidad_de_Partidos() {
        int total_partidos = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT count(*) AS  total_p  from w_sanchez1.partidos p ");
            if (result.next()) {
                total_partidos = result.getInt("total_p");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }
        return total_partidos;

    }

    public float promedio_goles() {
        float promedio = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT AVG(goles_local + goles_visitante) AS promedio_goles FROM w_sanchez1.partidos p ");
            if (result.next()) {
                promedio = result.getInt("promedio_goles");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return promedio;
    }

    public int numero_partido_ganados() {
        int partidos_ganados = 0;

        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(*) as p_ganados\n"
                    + "FROM w_sanchez1.partidos p \n"
                    + "WHERE goles_local <> goles_visitante;");
            if (result.next()) {
                partidos_ganados = result.getInt("p_ganados");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return partidos_ganados;
    }

    public int numero_partido_empatados() {
        int partidos_empatados = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(*) as p_empatados\n"
                    + "FROM w_sanchez1.partidos p \n"
                    + "WHERE goles_local = goles_visitante;");
            if (result.next()) {
                partidos_empatados = result.getInt("p_empatados");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return partidos_empatados;
    }

}
