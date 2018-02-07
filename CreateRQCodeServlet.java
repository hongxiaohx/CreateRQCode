import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;

import com.google.zxing.common.BitMatrix;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiaoge.bean.MatrixToImageWriter;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.util.Hashtable;


@WebServlet(name = "CreateRQCodeServlet")
public class CreateRQCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contents="哈哈hello";
        int width=300;
        int height=300;
        String format="jpg";
        Hashtable<EncodeHintType,Object> hints= new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");


  try {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
              BarcodeFormat.QR_CODE,
              width,
              height,
              hints);
      String logoPath = this.getServletContext().getRealPath("/WEB-INF/images");
      logoPath = logoPath + File.separator + "logo.bmp";
      OutputStream out = response.getOutputStream();
      MatrixToImageWriter.writeToStream(bitMatrix, format, out, logoPath);
  }
  catch (Exception ex){
      System.out.println(ex.getMessage());
  }



    }
}
