package apis;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 *
 * @author Adrian Flores Pizarro
 * @since Oct 15, 2015
 * @version 1.0
 */

public class curpApi {


    public static Map cookies;
    public String curp;
    private String captcha;

    public static String nombre;
    public static String aPaterno;
    public static String aMaterno;
    public static String sexo;
    public static String fNacimiento;
    public static String eNacimiento;
    public static String codeEntidad;
    public static String codeMunicipio;


    public String getCurp() {
        return curp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public String geteNacimiento() {
        return eNacimiento;
    }

    public String getCodeEntidad() {
        return codeEntidad;
    }

    public String getCodeMunicipio() {
        return codeMunicipio;
    }

    public static void downloadCaptcha()throws Exception {
        Connection.Response response = Jsoup.connect("https://consultas.curp.gob.mx/CurpSP/")
                .timeout(300000)
                .userAgent("Mozilla/5.0")
                .method(Connection.Method.GET).execute();


        cookies = response.cookies();




        Connection.Response resultImageResponse = Jsoup.connect("https://consultas.curp.gob.mx/CurpSP/imagenCatcha")
                .cookies(cookies)
                .ignoreContentType(true)
                .method(Connection.Method.GET).timeout(30000).execute();

        cookies.putAll(resultImageResponse.cookies());

        FileOutputStream out = (new FileOutputStream(new java.io.File("captcha.jpg")));
        out.write(resultImageResponse.bodyAsBytes());
        out.close();

        System.out.println("Captcha Fetched");

    }

    public static boolean getData(String captcha, String curp) throws Exception{
       int comp=0;A
        Connection conn = Jsoup.connect("https://consultas.curp.gob.mx/CurpSP/curp21.do?strCurp="+curp+"&strTipo=B&codigo="+captcha)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Host", "consultas.curp.gob.mx")
                .cookies(cookies)
                .timeout(30000)
                .method(Connection.Method.GET);


        Response response = conn.execute();
        cookies = response.cookies();
        System.out.println(response.cookies());
        Document doc= response.parse();


        int x=0;
        Elements elements = doc.select(".Nota");
        for (Element element : elements) {
        String text = element.text();
            switch(x){
                case 0:
                    curp=text;

                break;

                case 1:
                    aPaterno=text;
                break;

                case 2:
                    aMaterno=text;
                break;

                case 3:
                    nombre=text;
                break;

                case 4:
                    sexo=text;
                break;

                case 5:
                    fNacimiento=text;
                break;
                //////////////////Se omite el 6 nacionalidad
                case 7:
                    eNacimiento=text;
                break;
                //////////777 Se omitio el 8 tipo de documento
                case 9:
                    codeEntidad=text;
                break;

                case 10:
                    codeMunicipio=text;
                break;
               // break;
            }
        }

}


    }

    private void run() throws Exception, IOException {
        downloadCaptcha();
        //we set user/pass and captcha
        //Scanner capt=new Scanner(System.in);
        //captcha=capt.next();
        //getData(captcha);

    }

//    public static void main(String[] args) throws Exception {
//        captchaLogin main = new captchaLogin();
//        main.run();
//    }

}
