package rest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nhancao on 3/9/16.
 */
@Path( "/file" )
public class FileService
{
    @GET
    @Produces("*/*")
    public Response getFile()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File( classLoader.getResource( "META-INF/test.xls" ).getFile() );

        Response.ResponseBuilder response = Response.ok( (Object) file );
        response.header( "Content-Disposition",
            "attachment; filename=\"test.xls\"" );
        return response.build();
    }

    @GET
    @Path( "/xls" )
    public Response downloadPdfFile()
    {
        Response.ResponseBuilder response = Response.ok();
        ClassLoader classLoader = getClass().getClassLoader();
        File exfile = new File( classLoader.getResource( "META-INF/test.xls" ).getFile() );
        try
        {
            FileInputStream file = new FileInputStream( exfile );

            HSSFWorkbook workbook = new HSSFWorkbook( file );
            HSSFSheet sheet = workbook.getSheetAt( 0 );
            Cell cell = null;

            //Update the value of cell
            cell = sheet.getRow( 1 ).getCell( 1 );
            cell.setCellValue( "nhancv" );
            cell = sheet.getRow( 2 ).getCell( 1 );
            cell.setCellValue( "nhancv" );
            cell = sheet.getRow( 3 ).getCell( 1 );
            cell.setCellValue( "nhancv" );

            file.close();

            FileOutputStream outFile = new FileOutputStream( new File( classLoader.getResource( "META-INF" ).getPath() + "/test_tmp.xls" ) );
            workbook.write( outFile );
            outFile.close();
            exfile = new File( classLoader.getResource( "META-INF/test_tmp.xls" ).getFile() );

            response = Response.ok( (Object) exfile );
            response.header( "Content-Disposition", "attachment; filename=\"update.xls\"" );

        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return response.build();
    }
}
