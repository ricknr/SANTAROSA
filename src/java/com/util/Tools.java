package com.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException; /***************** ENCRIPTAR PASSWORD *****************/
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.DocumentBuilderFactory; /***************** ReadXML File Libraries *****************/
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 /***************** Export to Excel Libraries *****************/
//import jxl.*;
//import jxl.write.WritableSheet;
//import jxl.write.Label;
//import java.sql.ResultSetMetaData;
//import jxl.write.WritableWorkbook;
//import javax.servlet.http.HttpServletResponse;
//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;
//import jxl.format.Colour;
//import jxl.format.Border;
//import jxl.format.BorderLineStyle;
//import jxl.format.Alignment;
//import jxl.format.VerticalAlignment;
//import jxl.write.NumberFormats;
import org.apache.log4j.Logger; /***************** LOG *****************/
//import org.slf4j.ext.XLogger.Level;

public class Tools {
    public static Logger log  = Logger.getLogger(Tools.class);
    
    /************************************** EXCRIPTAR PASSWORD **************************************/
    public static String Encriptar(String texto){
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception e) {
            System.out.println("Error com.util.Tools_Encriptar: "+e.getMessage());
            e.printStackTrace();
        }
        return base64EncryptedString;
    }
    
    public static String Desencriptar(String textoEncriptado) throws Exception {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
            System.out.println("Error com.util.Tools_Desencriptar: "+ex.getMessage());
            ex.printStackTrace();
        }
        return base64EncryptedString;
    }
     
    public static String decode(String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = "jaas is the way".getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

        BigInteger n = new BigInteger(secret, 16);
        byte[] encoding = n.toByteArray();

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = cipher.doFinal(encoding);
        return new String(decode);
    }
    
    public static String createPassword(String password) {
        DigestInputStream dis = null;
        String out = null;
        try {
            if (password != null) {
                dis = new DigestInputStream(new ByteArrayInputStream(password.getBytes()), MessageDigest.getInstance("MD5"));
                int count = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((count = dis.read()) != -1) {
                    bos.write(count);
                }
                out = new String(Base64.encodeBase64(dis.getMessageDigest().digest()), "UTF-8").trim();
                bos.close();
                dis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return out;
    }
    
    public static String getMD5(String keyWord){
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(keyWord.getBytes());

            byte byteData[] = md.digest();

            for (int i=0;i<byteData.length;i++) {
                    String hex=Integer.toHexString(0xff & byteData[i]);
                    if(hex.length()==1) hexString.append('0');
                    hexString.append(hex);
            }

        } catch(Exception ex){
            return "";
        }
        return hexString.toString();
    }
    
    
    
    /************************************** EXPORT TO EXCEL **************************************/
//    public static WritableCellFormat getCellFormatGeneral() throws Exception {
//        WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
//        font.setColour(Colour.BLACK);
//        WritableCellFormat cellFormat = new WritableCellFormat(font);
//        cellFormat.setBackground(Colour.WHITE);
//        cellFormat.setFont(font);
//        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
//        cellFormat.setAlignment(Alignment.CENTRE);
//        
//        return cellFormat;
//    }
//    
//    public static WritableCellFormat getCellFormatNumber() throws Exception {
//        WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
//        font.setColour(Colour.BLACK);
//        WritableCellFormat cellFormat = new WritableCellFormat(font, NumberFormats.INTEGER);
//        cellFormat.setBackground(Colour.WHITE);
//        cellFormat.setFont(font);
//        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//        return cellFormat;
//    }
//    
//    public static WritableCellFormat getCellFormatHeader() throws Exception {
//        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
//        font.setColour(Colour.BLACK);
//        WritableCellFormat cellFormat = new WritableCellFormat(font);
//        cellFormat.setBackground(Colour.GRAY_25);
//        cellFormat.setAlignment(Alignment.CENTRE);        
//        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//        return cellFormat;
//    }
//    
//    public static void createExcel(HttpServletResponse response) throws Exception {
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename=Reporte.xls;");
////        ResultSetMetaData rsetmd                = rset.getMetaData();
//        WritableWorkbook workbook               = Workbook.createWorkbook(response.getOutputStream());
//        WritableSheet sheet                     = workbook.createSheet("Reporte", 0);
//        Label label                             = null;
//        WritableCellFormat cellFormatHeader     = Tools.getCellFormatHeader();
//        int i, il;
//        
//
//        for (i = 0; i <= 5; i++) {
//            label = new Label(i, 0, "Columna_1", cellFormatHeader); //col, row, label, format
//            sheet.setColumnView(i, 30); //se asigna el ancho, ancho * 7 = numero de pixeles que medirá la columna
//            sheet.addCell(label);
//        }
//        
//        int row = 0;
//
//        WritableCellFormat cellFormatGeneral = Tools.getCellFormatGeneral();
//        WritableCellFormat numberCellFormat = Tools.getCellFormatNumber();
//        jxl.write.Number numberCell = null;
//        
//        for(int s=0; s<=5; s++){
//            row++;
//            for (i = 0; i <= 7; i++) {
//                try {
//                    numberCell = new jxl.write.Number(i, row, 1.0, numberCellFormat); //col, row, label, format
//                    sheet.addCell(numberCell);
//                } catch (Exception e) {
//                    label = new Label(i, row, "Nombre", cellFormatGeneral); //col, row, label, format
//                    sheet.addCell(label);
//                }
//            }
//        }
//        
////        while (rset.next()) {
////            row++;
////            for (i = 0; i < il; i++) {
////                try {
////                    numberCell = new jxl.write.Number(i, row, rset.getDouble(i + 1), numberCellFormat); //col, row, label, format
////                    sheet.addCell(numberCell);
////                } catch (Exception e) {
////                    label = new Label(i, row, rset.getString(i + 1), cellFormatGeneral); //col, row, label, format
////                    sheet.addCell(label);
////                }
////            }
////        }
//        workbook.write();
//        workbook.close();
//    }
    
    /************************************** SAVE FILE - UPLOAD FILE **************************************/
    public static boolean existeArchivo(String path) {
        boolean resultado = false;
        
        try{
            String sFichero = path;
            File fichero = new File(sFichero);
            
            if (fichero.exists()) {
                if(fichero.delete()){
                    System.out.println("El fichero " + sFichero + " existia, y fue eliminado");
                    log.info("El fichero " + sFichero + " existia, y fue eliminado");
                    resultado = false;
                }else{
                    System.out.println("El fichero " + sFichero + " existe y no pudo ser eliminado, revisar permisos de carpeta");
                    log.info("El fichero " + sFichero + " existe y no pudo ser eliminado, revisar permisos de carpeta");
                    resultado = true;
                }
            }else{
                System.out.println("El fichero "+sFichero+" no existe");
                log.info("El fichero "+sFichero+" no existe");
                resultado = false;
            }            
        }catch(Exception e){
            System.out.println("Error iconEditorController - existeArchivo: "+e.getMessage());
            log.info("Error iconEditorController - existeArchivo: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
    
//    public static String getFileName(final Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
    
//    public static boolean saveFile(String fullPath, final Part filePart, String nombreArchivo) throws ServletException, IOException{
//        boolean resultado = false;
////        final String fileName = getFileName(filePart);
//        final String fileName = nombreArchivo;
//        OutputStream out = null;
//        InputStream filecontent = null;        
//        
//        try{
//            out = new FileOutputStream(new File(fullPath + File.separator + fileName));
//            filecontent = filePart.getInputStream();
//            int read = 0;
//            final byte[] bytes = new byte[1024];
//
//            while ((read = filecontent.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            
//            log.info("New file " + fileName + " created at " + fullPath);
//            System.out.println("New file " + fileName + " created at " + fullPath);
//            resultado = true;
//        }catch(FileNotFoundException fne){
//            resultado = false;
//            System.out.println("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");
//            System.out.println("Error: "+fne.getMessage());
//            
//            log.info("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");
//            log.info("Error: "+fne.getMessage());
//            fne.printStackTrace();
//        }finally{
//            if (out != null) {
//                out.close();
//            }
//            if (filecontent != null) {
//                filecontent.close();
//            }            
//        }
//        
//        return resultado;
//    }   
    
    /************************************** READ XML FILE **************************************/
    public static String readXML_iconEditorPaths() throws ParserConfigurationException, SAXException, IOException{
        String resultado = "";
        File fXmlFile = new File("C:/appserver/AdminConsole/iconEditorPaths.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        
        try{
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("path");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Path id: " + eElement.getAttribute("id"));
                    System.out.println("Path Name: " + eElement.getElementsByTagName("pathName").item(0).getTextContent());
                    System.out.println("Str Path: " + eElement.getElementsByTagName("strPath").item(0).getTextContent());
                    
                    resultado += "id:"+eElement.getAttribute("id")+", pathName:"+eElement.getElementsByTagName("pathName").item(0).getTextContent()+", strPath:"+eElement.getElementsByTagName("strPath").item(0).getTextContent()+";";
                }
            }
        }catch(Exception e){
            System.out.println("Error iconEditorController - readXML: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public static String readXML_mainRoadsStylePaths() throws ParserConfigurationException, SAXException, IOException{
        String resultado = "";
        File fXmlFile = new File("C:/appserver/AdminConsole/mainRoadsStylesPaths.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        
        try{
            doc.getDocumentElement().normalize();            
            NodeList nList = doc.getElementsByTagName("path");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    resultado += eElement.getElementsByTagName("strPath").item(0).getTextContent()+";";
                }
            }            
        }catch(Exception e){
            System.out.println("Error Tools - readXML_mainRoadsStylePaths: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    /************************************** RESIZE IMAGE **************************************/
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resizeImage(String inputImagePath,String outputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        System.out.println("InputImagePath: "+inputImagePath);
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = null;
        for(File file: inputFile.listFiles()) {
            inputImage = ImageIO.read(file);
        }
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
    
    /************************************** RENAME FILE **************************************/
    public static String renameFile(String oldFileName, String newName){
        String resultado = null;
        
        try{
            File oldfile = new File("C://appserver/AdminConsole/radars_point_"+oldFileName+".xml");
            File newFile = new File("C://appserver/AdminConsole/radars_point_"+newName+".xml");
            
            if (oldfile.renameTo(newFile)) {
                System.out.println("Rename succesful");
            } else {
                System.out.println("Rename failed");
            }
        }catch(Exception e){
            System.out.println("Error Tools_renameFile: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    /************************************** MOVE FILE FILE **************************************/
    public static String moveFileToAnotherFolder(String oldPath, String newPath){
        String result = null;
        InputStream inStream = null;
	OutputStream outStream = null;
        
        try{
            File afile =new File(oldPath);
    	    File bfile =new File(newPath);

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[1024];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }

    	    inStream.close();
    	    outStream.close();
        }catch(Exception e){
            System.out.println("Error Tools_moveFileToAnotherFolder: "+e.getMessage());
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    /************************************** MODIFY SLD FILES **************************************/
    public static String modifySLDFile_radarsPoint(String fullPath, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
            
            Node name = doc.getElementsByTagName("Name").item(0); // Get the name by tag directly            
            name.setTextContent("radars_point_"+roadName); // update name tag attribute
                        
            NodeList ogcLiteralTagList = doc.getElementsByTagName("ogc:Literal"); //Buscar todas las etiquetas <ogc:Literal>           
            
            for(int s=0; s<ogcLiteralTagList.getLength(); s++){ //Ciclo para recorrer las etiquetas y reemplazar las que lleven el nombre
                Node node = ogcLiteralTagList.item(s);
                if(node.getTextContent().equals("RIO SAN JOAQUIN")){ //Si la etiqueta tiene el valor 
                    node.setTextContent(roadName); //reemplazar el valor de la etiqueta por el nombre de la mainRoad
                }
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public static String modifySLDFile_signsPoint(String fullPath, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
                        
            NodeList ogcLiteralTagList = doc.getElementsByTagName("ogc:Literal"); //Buscar todas las etiquetas <ogc:Literal>           
            
            for(int s=0; s<ogcLiteralTagList.getLength(); s++){ //Ciclo para recorrer las etiquetas y reemplazar las que lleven el nombre
                Node node = ogcLiteralTagList.item(s);
                if(node.getTextContent().equals("EJE CENTRAL LAZARO CARDENAS")){ //Si la etiqueta tiene el valor 
                    node.setTextContent(roadName); //reemplazar el valor de la etiqueta por el nombre de la mainRoad
                }
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public static String modifySLDFile_tmsPoint(String fullPath, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
            
            Node name1 = doc.getElementsByTagName("Name").item(0); // Get the name by tag directly
            Node name2 = doc.getElementsByTagName("Name").item(1); // Get the name by tag directly
            Node title = doc.getElementsByTagName("Title").item(0); // Get the name by tag directly
            Node aabstract = doc.getElementsByTagName("Abstract").item(0); // Get the name by tag directly
                        
            name1.setTextContent("tms_point_"+roadName); // update name tag attribute
            name2.setTextContent(roadName); // update name tag attribute
            title.setTextContent(roadName); // update name tag attribute
            aabstract.setTextContent(roadName); // update name tag attribute
                        
            NodeList ogcLiteralTagList = doc.getElementsByTagName("ogc:Literal"); //Buscar todas las etiquetas <ogc:Literal>                       
            for(int s=0; s<ogcLiteralTagList.getLength(); s++){ //Ciclo para recorrer las etiquetas y reemplazar las que lleven el nombre
                Node node = ogcLiteralTagList.item(s);
                if(node.getTextContent().equals("TLALPAN")){ //Si la etiqueta tiene el valor 
                    node.setTextContent(roadName); //reemplazar el valor de la etiqueta por el nombre de la mainRoad
                }
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public static String modifySLDFile_trafficsHistoricPolyline(String fullPath, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
                        
            NodeList ogcLiteralTagList = doc.getElementsByTagName("ogc:Literal"); //Buscar todas las etiquetas <ogc:Literal>                       
            for(int s=0; s<ogcLiteralTagList.getLength(); s++){ //Ciclo para recorrer las etiquetas y reemplazar las que lleven el nombre
                Node node = ogcLiteralTagList.item(s);
                if(node.getTextContent().equals("MEXICO - TACUBA")){ //Si la etiqueta tiene el valor 
                    node.setTextContent(roadName); //reemplazar el valor de la etiqueta por el nombre de la mainRoad
                }
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public static String modifySLDFile_trafficsSensorPolyline(String fullPath, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
            
            NodeList ogcLiteralTagList = doc.getElementsByTagName("ogc:Literal"); //Buscar todas las etiquetas <ogc:Literal>                       
            for(int s=0; s<ogcLiteralTagList.getLength(); s++){ //Ciclo para recorrer las etiquetas y reemplazar las que lleven el nombre
                Node node = ogcLiteralTagList.item(s);
                if(node.getTextContent().equals("EJE TRONCAL METROPOLITANO-EJE 3 ORIENTE")){ //Si la etiqueta tiene el valor 
                    node.setTextContent(roadName); //reemplazar el valor de la etiqueta por el nombre de la mainRoad
                }
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    /************************************** MODIFY XML FILES **************************************/
    public static String modifyXMLFile_ALL(String fullPath, String nameTag, String roadName){
        String resultado = null;
        
        try{
            String filepath = fullPath; //Tomar el archivo xml
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);            
            
            Node name = doc.getElementsByTagName("name").item(0); // Get the name by tag directly            
            Node filename = doc.getElementsByTagName("filename").item(0); // Get the name by tag directly
            
            name.setTextContent(nameTag+"_"+roadName); // update name tag attribute
            filename.setTextContent(nameTag+"_"+roadName+".sld"); // update name tag attribute            
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
            transformer.transform(source, result);
            
            resultado = "success";
        }catch(ParserConfigurationException pce){
            System.out.println("Error[ParserConfiguration] Tools_readAndChangeXMLFile_1: "+pce.getMessage());
            pce.printStackTrace();
        }catch(TransformerException tfe){
            System.out.println("Error[Transformer] Tools_readAndChangeXMLFile_1: "+tfe.getMessage());
            tfe.printStackTrace();
        }catch(IOException ioe){
            System.out.println("Error[IO] Tools_readAndChangeXMLFile_1: "+ioe.getMessage());
            ioe.printStackTrace();
        }catch(SAXException sae){
            System.out.println("Error[SAX] Tools_readAndChangeXMLFile_1: "+sae.getMessage());
            sae.printStackTrace();
        }catch(Exception e){
            resultado = "error";
            System.out.println("Error Tools_readAndChangeXMLFile_1: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    
}
