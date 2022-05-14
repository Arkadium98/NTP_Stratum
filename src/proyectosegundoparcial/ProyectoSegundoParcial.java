/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jfree.ui.RefineryUtilities;
import static proyectosegundoparcial.Derby.createTable;
import static proyectosegundoparcial.Derby.deleteRegisters;
import static proyectosegundoparcial.Derby.getSQL;
import static proyectosegundoparcial.NTP.NTPConnection;

public class ProyectoSegundoParcial {

    public static void main(String[] args) throws SQLException {

//        String serverBat = new File("src/proyectosegundoparcial/server.bat")
//                .getAbsolutePath();
//        try {
//            String linea;
//            Process p = Runtime.getRuntime().exec("cmd.exe /K " + serverBat);
//            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
//                while ((linea = input.readLine()) != null) {
//                    System.out.println(linea);
//                }
//            }
//        } catch (IOException err) {
//            System.out.println(err);
//        }
        createTable();
        /* --------------------------------------SERVERS----------------------------------------------------------------------------- */
        String[] servers = {"192.100.201.202", "time.windows.com", "time.apple.com", "mx.pool.ntp.org", "time.google.com", "time.cloudflare.com",
            "time.facebook.com", "ntp1.net.berkeley.edu", "ntp.gsu.edu", "tick.usask.ca",
            "ntp.nict.jp", "clock.nyc.he.net", "clock.isc.org", "ntp0.as34288.net", "ntp.time.nl", "ntp1.jst.mfeed.ad.jp", "gnomon.cc.columbia.edu",
            "time-a-g.nist.gov", "time.nist.gov", "time.fu-be", "time.fu-berlin.de", "chime1.surfnet.nl", "ntp.nic.cz", "ntp.fizyka.umk.pl",
            "ntp1.usv.ro", "time.nrc.ca", "cronos.cenam.mx", "hora.roa.es", "ntp1.inrim.it", "ntp1.oma.be",
            "ntp.i2t.ehu.eus", "ntp.neel.ch", "ntp.neu.edu.cn", "ntp.nict.jp", "ntps1.pads.ufrj.br", "ntp.shoa.cl", "time.esa.int", "cr.pool.ntp.org", "us.pool.ntp.org"};

        /* --------------------------------------NTP CONNECTION----------------------------------------------------------------------------- */
        deleteRegisters();

        NTPConnection(servers);

        // ---------------PROMEDIO STRATUM--------------------------------------
        ResultSet result = getSQL("SELECT Stratum, AVG(precision) AS Precision,AVG(Delay)AS Delay FROM APP.NTPINFO GROUP BY stratum");

        ArrayList<PromedioStratum> stratArray = new ArrayList<PromedioStratum>();
        while (result.next()) {
            int stratum = Integer.parseInt(result.getString("stratum"));
            int precision = Integer.parseInt(result.getString("precision"));
            int delay = Integer.parseInt(result.getString("delay"));
            PromedioStratum obj = new PromedioStratum(stratum, precision, delay);
            stratArray.add(obj);
        }
        GraficaBarrasStratum demo = new GraficaBarrasStratum(
                "JFreeChart: Proyecto Segundo Parcial.java", stratArray);

        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        // ---------------DATOS INDIVIDUALES--------------------------------------
        ResultSet result2 = getSQL("SELECT nombre,stratum, precision,delay FROM APP.NTPINFO");
        ArrayList<DatoIndividual> ArrayIndividual = new ArrayList<DatoIndividual>();
        while (result2.next()) {
            String nombre = result2.getString("nombre");
            int stratum = Integer.parseInt(result2.getString("stratum"));
            int precision = Integer.parseInt(result2.getString("precision"));
            int delay = Integer.parseInt(result2.getString("delay"));

            DatoIndividual dato = new DatoIndividual(nombre, stratum, precision, delay);
            ArrayIndividual.add(dato);
        }
        // --------------- Precision --------------------
        GraficaBarrasPrecision demo2 = new GraficaBarrasPrecision(
                "JFreeChart: Proyecto Segundo Parcial.java", ArrayIndividual);
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);
        // --------------- Delay --------------------
        GraficaBarrasDelay demo3 = new GraficaBarrasDelay(
                "JFreeChart: Proyecto Segundo Parcial.java", ArrayIndividual);
        demo3.pack();
        RefineryUtilities.centerFrameOnScreen(demo3);
        demo3.setVisible(true);

    } // ---------------------------------------END OF MAIN----------------------------------------------------------

}
