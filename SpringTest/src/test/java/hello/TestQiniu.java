package hello;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

import java.net.URL;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by peipeiyang on 15/12/30.
 */
public class TestQiniu {
    public static void main(String[] args) {
        Auth auth = Auth.create("nUHz55Q4dk-p-ioTaohPDi3Icn1gXCwTChlI_ybc", "Jk5_DzgAnrPYObs3y2I10SEUEF33TJQvh5zQvvIf");
        UploadManager uploadManager = new UploadManager();
        File file = new File("/Users/peipeiyang/Documents/contacttest.docx");
        try {
            String fileName = System.currentTimeMillis() + ".docx";
            Response response = uploadManager.put(file, fileName, auth.uploadToken("volantis"));
            System.out.println(response.isOK());
            System.out.println(response.bodyString());
            if (response.isOK()){
                String pdfFilePath="7xnb70.com2.z0.glb.qiniucdn.com/"+fileName+"?yifangyun_preview/v2/";
                System.out.println(pdfFilePath);
                URL remoteUrl = new URL(pdfFilePath);
                ReadableByteChannel rbc = Channels.newChannel(remoteUrl.openStream());
                String fileout = "/tmp/test.pdf";
                FileOutputStream fos = new FileOutputStream(fileout);
                fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
                fos.flush();

            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
