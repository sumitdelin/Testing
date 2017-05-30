package test;

import java.io.*;
import org.apache.pdfbox.exceptions.InvalidPasswordException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class pdfTxtExtraction {

    public static void main(String[] args) throws Exception {
        PrintTextLocations obj = new PrintTextLocations();
        obj.myfun();
        System.out.println("sssssssss");
    }
}

class PrintTextLocations extends PDFTextStripper {

    public PrintTextLocations() throws IOException {
        super.setSortByPosition(true);
    }

    public static void myfun() throws Exception {
        PDDocument document = null;
        try {
//            File input = new File("C:\\Users\\e6495\\Downloads\\DAZ1216_DAZ_001.pdf");
            File input = new File("C:\\Users\\e6495\\Desktop\\CapIssue\\CLN0617_CLN_026.pdf");
            document = PDDocument.load(input);
//            if (document.isEncrypted()) {
//                    document.decrypt("");
//            }
            List allPages = document.getDocumentCatalog().getAllPages();
            for (int i = 0; i < allPages.size(); i++) {
                PDPage page = (PDPage) allPages.get(i);
                System.out.println("Processing page: " + i);
                System.out.println("W : " + page.getMediaBox().getWidth());
                System.out.println("H : " + page.getMediaBox().getHeight());
                System.out.println("W : " + page.getMediaBox().getWidth() * 300 / 72);
                System.out.println("H : " + page.getMediaBox().getHeight() * 300 / 72);
                System.out.println("W : " + page.getCropBox().getWidth());
                System.out.println("H : " + page.getCropBox().getHeight());
                System.out.println("W : " + page.getCropBox().getWidth() * 300 / 72);
                System.out.println("H : " + page.getCropBox().getHeight() * 300 / 72);

//                PDStream contents = page.getContents();
//                if (contents != null) {
//                    printer.processStream(page, page.findResources(), page.getContents().getStream());
//                }

                PDFTextStripper pdfStripper = new PDFTextStripper() {

                    boolean startPara = true;
                    boolean endPara = false;

                    @Override
                    protected void startPage(PDPage page) throws IOException {
                        startPara = true;
                        super.startPage(page); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    protected void endPage(PDPage page) throws IOException {
                        endPara = true;
                        super.endPage(page); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public String getParagraphStart() {
                        startPara = true;
                        return super.getParagraphStart(); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public String getParagraphEnd() {
                        endPara = true;
                        return super.getParagraphEnd(); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {

                        for (TextPosition txt : textPositions) {
                            if (endPara) {
                                System.out.println("</p>");
                                System.out.println();
                                endPara = false;
                            }

                            if (startPara) {
                                System.out.print("<p>");
                                startPara = false;
                            }
                               System.out.print(txt.getCharacter());
                        }
                        
                       super.writeString(text, textPositions); //To change body of generated methods, choose Tools | Templates.
                       
                    }

                };

//                pdfStripper.setParagraphStart("/t");
                for (String line : pdfStripper.getText(document).split(pdfStripper.getParagraphStart())) {
//                    System.out.print(line);
//                    System.out.println("********************************************************************");
                }

            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    @Override
    protected void processTextPosition(TextPosition text) {
        System.out.println(" String [x: " + text.getXDirAdj() + ", y: "
                + text.getY() + ", height:" + text.getHeightDir()
                + ", space: " + text.getWidthOfSpace() + ", width: "
                + text.getWidthDirAdj() + ", yScale: " + text.getYScale() + "]"
                + text.getCharacter());
    }

}
