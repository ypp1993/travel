package hello.upload;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by peipeiyang on 15/12/30.
 */
@Controller
public class TableHandle {
    @RequestMapping("/upfile")
    public String hitable(@RequestParam(value="file", required=true) MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            XWPFDocument docx=null;
            String result1 = new String(bytes,"UTF-8");
            String result2 = new String(bytes,"ISO-8859-1");
            //System.out.println("string2:" + result2);
            //System.out.println("string1:" + result1);
            //System.out.println("getContentType:" + multipartFile.getContentType());
            System.out.println("getOriginalFilename:"+multipartFile.getOriginalFilename());

            System.out.println("multipartFile.getName() :"+multipartFile.getName());
            InputStream in = multipartFile.getInputStream();
            System.out.println("in.toString():"+in.toString());

            String path = "./src/main" ;
            String filename = multipartFile.getOriginalFilename();
            InputStream fis = multipartFile.getInputStream();
            if(multipartFile.getSize()>0){
                SaveFileFromInputStream(fis,path,filename );
            }else{
                System.out.println("multipartFile is null ");
            }

            /* 删除文件
            File file = new File(path+ "/"+ filename);
            if(file.delete()){
                System.out.println("delete file successful");

            }else{
                System.out.println("delete file failed");
            }*/
            /*
            if(inputfile!=null){
                fis = new FileInputStream(inputfile);
                docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphs = docx.getParagraphs();
                Iterator s=paragraphs.iterator();
                //Iterator 内部不能修改操作;
                while(s.hasNext()) {
                    XWPFParagraph par = (XWPFParagraph) s.next();
                    String parText = par.getText();
                    System.out.println("PText::  " + parText);
                }
            }
*/
        }catch (Exception e){
            e.printStackTrace();
        }

        return "hi";
    }
    public void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException
    {
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread=stream.read(buffer))!=-1)
        {
            bytesum+=byteread;
            fs.write(buffer,0,byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }
}
