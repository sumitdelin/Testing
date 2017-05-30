package pdfa;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.XmpSerializer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.xml.transform.TransformerException;
import org.apache.pdfbox.exceptions.COSVisitorException;

/**
 * This is an example on how to add metadata to a document.
 *
 * @author Ben Litchfield
 * 
 */
public final class AddMetadataFromDocInfo
{
    private AddMetadataFromDocInfo()
    {
        //utility class
    }

    /**
     * This will print the documents data.
     *
     * @param args The command line arguments.
     *
     * @throws IOException If there is an error parsing the document.
     * @throws TransformerException
     */
    public static void main( String[] args ) throws IOException, TransformerException, COSVisitorException
    {
        if( args.length != 0 )
        {
            usage();
        }
        else
        {
            try (PDDocument document = PDDocument.load(new File("D:\\practical\\Testing\\Pages from Binder1_C.pdf")))
            {
                if (document.isEncrypted())
                {
                    System.err.println( "Error: Cannot add metadata to encrypted document." );
                    System.exit( 1 );
                }
                PDDocumentCatalog catalog = document.getDocumentCatalog();
                PDDocumentInformation info = document.getDocumentInformation();
                
                XMPMetadata metadata = XMPMetadata.createXMPMetadata();

                AdobePDFSchema pdfSchema = metadata.createAndAddAdobePDFSchema();
//                pdfSchema.setKeywords( info.getKeywords() );
//                pdfSchema.setProducer( info.getProducer() );

                XMPBasicSchema basicSchema = metadata.createAndAddXMPBasicSchema();
                basicSchema.setModifyDate( info.getModificationDate() );
                basicSchema.setCreateDate( info.getCreationDate() );
                basicSchema.setCreatorTool( info.getCreator() );
                basicSchema.setMetadataDate( new GregorianCalendar() );

                DublinCoreSchema dcSchema = metadata.createAndAddDublinCoreSchema();
//                dcSchema.setTitle( info.getTitle() );
                dcSchema.addCreator( "PDFBox" );
//                dcSchema.setDescription( info.getSubject() );

                PDMetadata metadataStream = new PDMetadata(document);
                catalog.setMetadata( metadataStream );
                
                XmpSerializer serializer = new XmpSerializer();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                serializer.serialize(metadata, baos, false);
                metadataStream.importXMPMetadata( baos.toByteArray() );

                
                document.save("D:\\practical\\Testing\\Pages from Binder1_C_.pdf");
            }
        }
    }

    /**
     * This will print the usage for this document.
     */
    private static void usage()
    {
        System.err.println( "Usage: java " + AddMetadataFromDocInfo.class.getName() + " <input-pdf> <output-pdf>" );
    }
}