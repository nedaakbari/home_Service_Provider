package ir.maktab.homeServiceProvider.util;

import ir.maktab.homeServiceProvider.model.entity.Person.Expert;

import java.io.*;


public class ImageWrapper {
   // private int maxFileSize =350 * 350;

    public static void saveImage(String picName, Expert expert) {
        File file = new File("C:\\image\\" + picName + "");
        int length = (int) file.length();
        if (length < 15000 * 15000) {
            byte[] imageData = new byte[length];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(imageData);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            expert.setImage(imageData);
        } else
            throw new RuntimeException("image is too large to upload");
    }


  /*  public byte[] fileToBytes(String filename) throws IOException {
        final byte[] buffer = new byte[256];
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (InputStream in = new FileInputStream(new File(filename))) {
                int bytesRead;
                while ((bytesRead = in.read(buffer)) > 0)
                    out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }*/

}