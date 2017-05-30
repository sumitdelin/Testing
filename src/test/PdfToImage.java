package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;


@SuppressWarnings("unchecked")
public class PdfToImage {
//
//    public static void main(String[] args) throws Exception {
//        myfun();
//    }

    static void myfun() throws Exception {
        try {
            String sourceDir
                    = "D:\\practical\\DAZ20170412_In\\DAZ1216_DAZ_176.pdf"; // Pdf files are read from this folder 
            String destinationDir = "D:\\practical\\DAZ20170412_In\\"; // converted images from pdf document are saved here

            File sourceFile = new File(sourceDir);
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> "
                        + destinationFile.getAbsolutePath());
            }
            if (sourceFile.exists()) {
                System.out.println("Images copied to Folder: "
                        + destinationFile.getName());
                PDDocument document = PDDocument.load(sourceFile);
                //PDDocument document =PDDocument.getPDFDoc(new File("generated3.pdf"));
                //List<PDPage> list = document.getDocumentCatalog().getAllPages();
                List<PDPage> list = document.getDocumentCatalog().getAllPages();
                System.out.println("Total files to be converted -> " + list.size());

                String fileName = sourceFile.getName().replace(".pdf",
                        "");
                int pageNumber = 1;
                for (PDPage page : list) {
                    BufferedImage image = page.convertToImage(BufferedImage.TYPE_INT_RGB,300);
                    File outputfile = new File(destinationDir + fileName
                            + "_" + pageNumber + ".jpg");
                    System.out.println("Image Created -> " + outputfile.getName());
                    ImageIO.write(image, "jpg", outputfile);
                    pageNumber++;
                    
                }
                document.close();
                System.out.println("Converted Images are saved at -> "
                        + destinationFile.getAbsolutePath());
            } else {
                System.err.println(sourceFile.getName() + " File not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
