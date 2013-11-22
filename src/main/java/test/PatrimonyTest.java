package com.patrimony.models;


public class PatrimonyTest  {

    public testPatrimony(){
        
        try {
            String id = "Lua";
            Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class).field("id").equal(id);
            
        }
        catch(Error e) {
            System.out.println("Impossível retornar descrição do bem");
        }
        
                
        try {
            Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class);
        }
        catch(Error e) {
            System.out.println("Impossível retornar lista de bens");
        }

    }
    
    public testDB(){
        
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://app18759691");
            MongoClient client = new MongoClient(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public testLogin(){
        try{
            Query<User> query = DB.getDatastore().createQuery(User.class).field("username").equal               ("NomeUsuarioIncorreto").field("password").equal("SenhaIncorreta");
        }catch(exception e){
            System.out.println("Dados Incorretos\n");
        }
    }
    
}  
    


