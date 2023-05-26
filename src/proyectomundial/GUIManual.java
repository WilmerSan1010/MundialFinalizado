package proyectomundial;

import static com.sun.tools.javac.code.TypeAnnotationPosition.field;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import proyectomundial.DAO.ResulltadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;
import proyectomundial.model.Resultados;

public class GUIManual extends JFrame {
    
    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResulltadosDAO resultadoDAO = new ResulltadosDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los Resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;
    
    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;
    
    private JPanel jPanelMenuHome;
    private JLabel btnHome;
    
    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;
    
    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;
    
    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;
    
    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;
    
    private JPanel jPanelMain;
    
    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();
        
    }
    
    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();
        
        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();
        
        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();
        
        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();
        
        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();
        
        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();
        
        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();
        JLabel t_p = new JLabel();
        JLabel t_pro = new JLabel();
        
        JPanel p_ganados = new JPanel();
        JLabel texto_partidos_ganados = new JLabel();
        JLabel t_partidos_ganados = new JLabel();
        
        JPanel p_empatados = new JPanel();
        JLabel texto_partidos_empatados = new JLabel();
        JLabel t_partidos_empatados = new JLabel();
        
        JPanel p_m_goles = new JPanel();
        p_m_goles.setPreferredSize(new Dimension(160, 50));
        JLabel texto_mas_goles = new JLabel();
        JLabel t_mas_goles = new JLabel();
        JTable table = new JTable();
        
        JPanel cantidad_selecciones = new JPanel();
        JLabel texto_selecciones = new JLabel();
        JLabel t_seleccion = new JLabel();
        JPanel continente_mas_G = new JPanel();
        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los Resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de Resultados
        pintarMenuDashboardRes();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();
        
        setTitle("Mundial");
        pack();
        setVisible(true);
    }
    
    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);
        
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);
        
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();
        
        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/mundial.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);
        
        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);
        
        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();
            
            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);
            
            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });
            
            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);
        
        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadoDAO.getResultadosMatriz();
        // Si no hay Resultados cargados, muestra el botón de carga de Resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();
            
            if (resultados == null) {
                
                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);
                
                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }
            
            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de Resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);
        
        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {
        jLabelTop.setText("Dash seleciones");
        /*JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de selecciones \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Total de selecciones Cargadas \n"                  √ HEHCO 
                + "2. Número de selecciones por continente (Se puede usar una tabla para pintar esto) \n"√ HEHCO 
                + "3. Cantidad de nacionalidades diferentes de los directores técnicos \n"√ HEHCO 
                + "4. Ranking de nacionalidades de directores técnicos \n\n"√ HEHCO 
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
         */
        
        JPanel cantidad_selecciones = new JPanel();
        JLabel texto_selecciones = new JLabel();
        JLabel t_seleccion = new JLabel();
        
        t_seleccion.setText("Cantidad de selecciones");
        texto_selecciones.setText("" + seleccionDAO.Cantidad_de_selecciones());
        cantidad_selecciones.add(t_seleccion);
        cantidad_selecciones.add(texto_selecciones);
        cantidad_selecciones.setPreferredSize(new Dimension(140, 50));
        cantidad_selecciones.setBackground(new java.awt.Color(235, 245, 251));
        
        List seleccion = seleccionDAO.selecciones_por_continente();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Continente");
        modeloTabla.addColumn("Cantidad_Selecciones");
        
        JTable table = new JTable(modeloTabla);
        table.setRowHeight(30);
        table.setBackground(new java.awt.Color(235, 245, 251));
// Ajustar el ancho de las columnas
        TableColumn columnaContinente = table.getColumnModel().getColumn(0);
        columnaContinente.setPreferredWidth(10); // Ancho de la columna Continente

        TableColumn columnaCantidad = table.getColumnModel().getColumn(1);
        columnaCantidad.setPreferredWidth(10); // Ancho de la columna Cantidad_Selecciones

        for (int i = 0; i < seleccion.size(); i += 2) {
            String continente = (String) seleccion.get(i);
            int cantidadSelecciones = (int) seleccion.get(i + 1);
            modeloTabla.addRow(new Object[]{continente, cantidadSelecciones});
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panelSelecciones = new JPanel();
        panelSelecciones.add(scrollPane);
        panelSelecciones.setPreferredSize(new Dimension(450, 200));
        
        JPanel cantidad_nacionalidad_dt = new JPanel();
        JLabel texto_cantidad_nacionalidad_dt = new JLabel();
        JLabel t_cantidad_nacionalidad_dt = new JLabel();
        t_cantidad_nacionalidad_dt.setText("Cantidad de nacionalidad ");
        texto_cantidad_nacionalidad_dt.setText("" + seleccionDAO.nacionalidades_dt());
        cantidad_nacionalidad_dt.add(t_cantidad_nacionalidad_dt);
        cantidad_nacionalidad_dt.add(texto_cantidad_nacionalidad_dt);
        cantidad_nacionalidad_dt.setPreferredSize(new Dimension(145, 50));
        cantidad_nacionalidad_dt.setBackground(new java.awt.Color(235, 245, 251));
        
        
        
        List nacionalidadDT = seleccionDAO.Ranking_nacionalidad();
        DefaultTableModel modeloTablaDT = new DefaultTableModel();
        modeloTablaDT.addColumn("nacionalidad");
        modeloTablaDT.addColumn("cantidad");
        
        JTable tableDT = new JTable(modeloTablaDT);
        tableDT.setRowHeight(30);
        tableDT.setBackground(new java.awt.Color(235, 245, 251));
// Ajustar el ancho de las columnas
        TableColumn columnaContinenteDT = tableDT.getColumnModel().getColumn(0);
        columnaContinenteDT.setPreferredWidth(10); // Ancho de la columna Continente

        TableColumn columnaCantidadDT = tableDT.getColumnModel().getColumn(1);
        columnaCantidadDT.setPreferredWidth(10); // Ancho de la columna Cantidad_Selecciones

        for (int i = 0; i < nacionalidadDT.size(); i += 2) {
            String continenteDT = (String) nacionalidadDT.get(i);
            int cantidadSeleccionesDT = (int) nacionalidadDT.get(i + 1);
            modeloTablaDT.addRow(new Object[]{continenteDT, cantidadSeleccionesDT});
        }
        
        JScrollPane scrollPaneDT = new JScrollPane(tableDT);
        JPanel panelDT = new JPanel();
        panelDT.add(scrollPaneDT);
        panelDT.setPreferredSize(new Dimension(480, 360));
        
        jPanelMain.removeAll();
        jPanelMain.add(cantidad_selecciones);
        jPanelMain.add(cantidad_nacionalidad_dt);
       
        jPanelMain.add(panelSelecciones);
         jPanelMain.add(panelDT);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);
        
        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");
        /*JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"               √ HECHO 
                + "2. Promedio de goles por partido \n"             √HECHO 
                + "3. Partido con más goles y partido con menos goles \n"    √HECHO 
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"   √HECHO
                + "5. Selección o selecciones con más goles y con menos goles \n" √HECHO 
                + "6. Selección con más puntos y menos puntos \n"   √HECHO 
                + "7. Continente o continentes con más goles y menos goles \n"   √HECHO 
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"√HECHO 
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
         */
        //CANTIDAD DE PARTIDOS
        JPanel c_partidos = new JPanel();
        
        JLabel texto_partidos = new JLabel();
        JLabel t_p = new JLabel();
        t_p.setText("Cantidad de partidos");
        texto_partidos.setText("" + resultadoDAO.Cantidad_de_Partidos());
        c_partidos.add(t_p);
        c_partidos.add(texto_partidos);
        
        c_partidos.setPreferredSize(new Dimension(120, 50));
        c_partidos.setBackground(new java.awt.Color(235, 245, 251));

        //PROMEDIO DE GOLES 
        JPanel p_goles = new JPanel();
        p_goles.setPreferredSize(new Dimension(120, 50));
        JLabel texto_promedio = new JLabel();
        JLabel t_pro = new JLabel();
        p_goles.add(t_pro);
        p_goles.add(texto_promedio);
        t_pro.setText("promedio de goles ");
        texto_promedio.setText("" + resultadoDAO.promedio_goles());
        p_goles.setBackground(new java.awt.Color(235, 245, 251));
        //numero de partido ganados 
        JPanel p_ganados = new JPanel();
        p_ganados.setPreferredSize(new Dimension(160, 50));
        JLabel texto_partidos_ganados = new JLabel();
        JLabel t_partidos_ganados = new JLabel();
        texto_partidos_ganados.setText("            " + resultadoDAO.numero_partido_ganados());
        t_partidos_ganados.setText("N° de partidos ganados");
        p_ganados.add(t_partidos_ganados);
        p_ganados.add(texto_partidos_ganados);
        p_ganados.setBackground(new java.awt.Color(235, 245, 251));

        //numero de partidos empatados
        JPanel p_empatados = new JPanel();
        p_empatados.setPreferredSize(new Dimension(160, 50));
        JLabel texto_partidos_empatados = new JLabel();
        JLabel t_partidos_empatados = new JLabel();
        texto_partidos_empatados.setText("" + resultadoDAO.numero_partido_empatados());
        t_partidos_empatados.setText("N° de partidos empatados");
        p_empatados.add(t_partidos_empatados);
        p_empatados.add(texto_partidos_empatados);
        p_empatados.setBackground(new java.awt.Color(235, 245, 251));
        // partidos mas goles
        List resultados = resultadoDAO.partido_mas_goles();

// Crear un nuevo modelo de tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Partido");
        modeloTabla.addColumn("Goles");

// Agregar fila del partido con más goles
        modeloTabla.addRow(new Object[]{"Partido más goles", resultados.get(0) + " (" + resultados.get(2) + ")"});

// Agregar fila del partido con menos goles
        modeloTabla.addRow(new Object[]{"Partido menos goles", resultados.get(2) + " (" + resultados.get(3) + ")"});

// Crear la tabla y establecer el modelo de tabla
        JTable table = new JTable(modeloTabla);
        table.setBackground(new java.awt.Color(235, 245, 251));
// Establecer ancho de las columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(0); // Columna "Partido"
        table.getColumnModel().getColumn(1).setPreferredWidth(0); // Columna "Goles"

// Establecer altura de las filas
        table.setRowHeight(30);

// Crear un panel con barra de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane(table);

// Crear el panel principal y agregar el panel con la tabla
        JPanel panel_mas_goles = new JPanel();
        panel_mas_goles.add(scrollPane);

// Establecer tamaño preferido del panel principal
        panel_mas_goles.setPreferredSize(new Dimension(450, 100));
        
        List continenteMASGoles = resultadoDAO.Continente_mas_goles();

        // Crear un nuevo modelo de tabla
        DefaultTableModel modeloTablaContinente = new DefaultTableModel();
        modeloTablaContinente.addColumn("Continente");
        modeloTablaContinente.addColumn("Total Goles");

        // Agregar filas con los datos de los continentes y sus goles
        for (int i = 0; i < continenteMASGoles.size(); i += 2) {
            String continente = (String) continenteMASGoles.get(i);
            int totalGoles = (int) continenteMASGoles.get(i + 1);
            modeloTablaContinente.addRow(new Object[]{continente, totalGoles});
        }
        // Crear la tabla y establecer el modelo de tabla
        JTable tableContinente = new JTable(modeloTablaContinente);
        tableContinente.setBackground(new java.awt.Color(235, 245, 251));
        // Establecer ancho de las columnas
        tableContinente.getColumnModel().getColumn(0).setPreferredWidth(60); // Columna "Continente"
        tableContinente.getColumnModel().getColumn(1).setPreferredWidth(60); // Columna "Total Goles"

        // Crear un panel con barra de desplazamiento para la tabla
        JScrollPane scrollPaneContinente = new JScrollPane(tableContinente);

        // Crear el panel principal y agregar el panel con la tabla
        JPanel continente_mas_G = new JPanel();
        continente_mas_G.add(scrollPaneContinente);

        // Establecer tamaño preferido del panel principal
        continente_mas_G.setPreferredSize(new Dimension(450, 200));
        
        List seleMAXMIN = resultadoDAO.seleccion_mas_goles_menos_goles();

        // Crear un nuevo modelo de tabla
        DefaultTableModel modeloTablaselecciones = new DefaultTableModel();
        modeloTablaselecciones.addColumn("Continente");
        modeloTablaselecciones.addColumn("Total Goles");

        // Agregar filas con los datos de los continentes y sus goles
        for (int i = 0; i < seleMAXMIN.size(); i += 2) {
            String seleccion = (String) seleMAXMIN.get(i);
            int seleccionGoles = (int) seleMAXMIN.get(i + 1);
            modeloTablaselecciones.addRow(new Object[]{seleccion, seleccionGoles});
        }

        // Crear la tabla y establecer el modelo de tabla
        JTable tableselecc = new JTable(modeloTablaselecciones);
        tableselecc.setBackground(new java.awt.Color(235, 245, 251));
        // Establecer ancho de las columnas
        tableselecc.getColumnModel().getColumn(0).setPreferredWidth(60); // Columna "Continente"
        tableselecc.getColumnModel().getColumn(1).setPreferredWidth(60); // Columna "Total Goles"

        // Crear un panel con barra de desplazamiento para la tabla
        JScrollPane scrollPaneselecc = new JScrollPane(tableselecc);

        // Crear el panel principal y agregar el panel con la tabla
        JPanel sele_max_min = new JPanel();
        sele_max_min.add(scrollPaneselecc);

        // Establecer tamaño preferido del panel principal
        sele_max_min.setPreferredSize(new Dimension(450, 200));
        
        List seleccionMAXMINPuntos = resultadoDAO.seleccion_mas_puntos_menos_puntos();

        // Crear un nuevo modelo de tabla
        DefaultTableModel modeloTablaseleccionMAXMIN = new DefaultTableModel();
        
        modeloTablaseleccionMAXMIN.addColumn("seleccion");
        modeloTablaseleccionMAXMIN.addColumn("Total Puntos");

        // Agregar filas con los datos de los continentes y sus goles
        for (int i = 0; i < seleccionMAXMINPuntos.size(); i += 2) {
            String SELEMM = (String) seleccionMAXMINPuntos.get(i);
            int totalPuntos = (int) seleccionMAXMINPuntos.get(i + 1);
            modeloTablaseleccionMAXMIN.addRow(new Object[]{SELEMM, totalPuntos});
        }

        // Crear la tabla y establecer el modelo de tabla
        JTable tableseleccionMAXMIN = new JTable(modeloTablaseleccionMAXMIN);
        tableseleccionMAXMIN.setBackground(new java.awt.Color(235, 245, 251));
        // Establecer ancho de las columnas
        tableseleccionMAXMIN.getColumnModel().getColumn(0).setPreferredWidth(60); // Columna "Continente"
        tableseleccionMAXMIN.getColumnModel().getColumn(1).setPreferredWidth(60); // Columna "Total Goles"

        // Crear un panel con barra de desplazamiento para la tabla
        JScrollPane scrollPaneSeleccionMAXMIN = new JScrollPane(tableseleccionMAXMIN);

        // Crear el panel principal y agregar el panel con la tabla
        JPanel sele_MAX_MIN = new JPanel();
        sele_MAX_MIN.add(scrollPaneSeleccionMAXMIN);

        // Establecer tamaño preferido del panel principal
        sele_MAX_MIN.setPreferredSize(new Dimension(450, 200));
        
        DefaultTableModel modeloTablaClasificados = resultadoDAO.obtenerModeloTablaClasificados();

// Crear la tabla y establecer el modelo de tabla
        JTable tableClasificados = new JTable(modeloTablaClasificados);
        tableClasificados.setBackground(new java.awt.Color(235, 245, 251));
// Establecer ancho de las columnas
        tableClasificados.getColumnModel().getColumn(0).setPreferredWidth(40); // Columna "Grupo"
        tableClasificados.getColumnModel().getColumn(1).setPreferredWidth(40); // Columna "Equipo"
        tableClasificados.getColumnModel().getColumn(2).setPreferredWidth(40); // Columna "Puntos"

// Crear un panel con barra de desplazamiento para la tabla
        JScrollPane scrollPaneClasificados = new JScrollPane(tableClasificados);

// Crear el panel principal y agregar el panel con la tabla
        JPanel panelClasificados = new JPanel();
        panelClasificados.add(scrollPaneClasificados);

// Establecer tamaño preferido del panel principal
        panelClasificados.setPreferredSize(new Dimension(450, 300));
        
        jPanelMain.removeAll();
        jPanelMain.add(c_partidos);
        jPanelMain.add(p_goles);
        jPanelMain.add(p_ganados);
        jPanelMain.add(p_empatados);
        jPanelMain.add(panel_mas_goles);
        jPanelMain.add(continente_mas_G);
        jPanelMain.add(sele_max_min);
        jPanelMain.add(panelClasificados);
        jPanelMain.add(sele_MAX_MIN);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {
        
        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);
        
        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");
                
                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }
            
            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {
        
        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);
        
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                /* List<Resultados>resultadosBusqueda=resultadosDAO.getResultadosBusqueda(field.getText());*/

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);
        
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.setText("");
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");
                
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }
                
                table.setModel(modeloTabla);
            }
        });
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {
        
        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);
        
        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();
            
            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");
                
                Resultados resultados = new Resultados(columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
                if (resultadoDAO.registrarResultados(resultados)) {
                    System.out.println("Resultado " + resultados.getGrupo() + " registrada");
                } else {
                    System.out.println("Error " + resultados.getGrupo());
                }
                
            }
            resultados = resultadoDAO.getResultadosMatriz();
            pintarTablaResultados();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * Resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {
        String[] columnNames = {"Grupo",
            "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);
        
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                //  List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                List<Resultados> resultadosBusqueda = resultadoDAO.getresultadosbusquedas(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);
        
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                field.setText("");
                List<Resultados> resultadosBusqueda = resultadoDAO.getresultadosbusquedas("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
        
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(1000, 950)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());
        
        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(1000, 950)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }
//
    
    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");
        
        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(1000, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
