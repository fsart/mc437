package com.patrimony.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.sun.jersey.api.JResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import com.patrimony.db.MongoResource;
import com.patrimony.models.Patrimony;

@Path("/patrimonies")
@Consumes("application/json")
public class PatrimonyService {

    @GET
    @Produces("application/json")
    public List<Patrimony> list() {
        Datastore DB = MongoResource.INSTANCE.getDatastore();
        Query<Patrimony> query = DB.createQuery(Patrimony.class);

        return query.asList();
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
        File tempFile = createTempFile(uploadedInputStream);
    
        Xlsx x = new Xlsx();

		x.parse(tempFile);
		
		for(String key : x.sheets().keySet()) {
		    String[][] mat;
			mat = x.sheets().get(key);
			
			for(int i = 1; i < mat.length; i++) {
			    Patrimony patrimony = new Patrimony(mat[i]);
			    getDatastore().save(patrimony);
			}
		}
    }
    
    private File createTempFile(InputStream uploadedInputStream) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile((new Date()).toString(), ".tmp");
            tempFile.deleteOnExit();
            
			OutputStream out = new FileOutputStream(tempFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			/*out.flush();
			out.close();*/
		} catch (IOException e) {
		    tempFile = null;
 			e.printStackTrace();
		}
		
		return tempFile;
    }
}
