package com.patrimony.services;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.utils.Xlsx;
import com.patrimony.models.Patrimony;

@Path("patrimonies")
public class PatrimonyService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Patrimony> list() {
        Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class);

        return query.asList();
    }

    @POST
    @Consumes("multipart/form-data")
    public List<String> upload(File file) throws Exception {
        boolean hasConflict = false;
        ArrayList<String> conflicts = new ArrayList<String>();
        Xlsx xlsx = new Xlsx();
        System.out.println("--------------------------------------");
        System.out.println(file.toString());
        xlsx.parse(file);

        System.out.println("------------------------ oi ------------------------");
        for(String sheetKey : xlsx.sheets().keySet()) {
            String[][] sheet = xlsx.sheets().get(sheetKey);

            for(int i = 1; i < sheet.length; i++) {
                Patrimony patrimony = new Patrimony(sheet[i]);
                System.out.println(patrimony.id);

                for(String conflict : patrimony.conflicts()) {
                    hasConflict = true;
                    System.out.println(conflict);
                    conflicts.add(conflict);
                }
            }
        }

        System.out.println("------------------------ oi ------------------------");
        if (!hasConflict) {
            System.out.println(" não tenho conflitos");
            for(String sheetKey : xlsx.sheets().keySet()) {
                String[][] sheet = xlsx.sheets().get(sheetKey);

                for(int i = 1; i < sheet.length; i++) {
                    Patrimony patrimony = new Patrimony(sheet[i]);

                    DB.getDatastore().save(patrimony);
                }
            }
        }
        return conflicts;
    }
}
