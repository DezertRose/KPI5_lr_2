package com.mycompany.app.Hendler;

import com.mycompany.app.DatabaseConection;
import com.mycompany.app.Facade;
import com.mycompany.app.Objects.Hostel;
import com.mycompany.app.Objects.Students;
import com.mycompany.app.Objects.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Hendlers {

    public static class StudentsHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exc) throws IOException {
            exc.getResponseHeaders().add("Access-Control-Allow-Headers","Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            exc.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");

            String query = Support.toParseRequest(exc);

            if("GET".equals(exc.getRequestMethod())){
                try{
                    ArrayList<Students> getStudents = Facade.allStudents;
                    Support.makeResponse(exc, getStudents);
                    DatabaseConection.closeConnection();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                if("DELETE".equals(exc.getRequestMethod())){
                    try{
                        String deleteData = CrudData.deleteStudents(query, Facade.allStudents);
                        Support.makeResponse(exc, deleteData);
                        DatabaseConection.closeConnection();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                else{Support.makeResponse(exc, "Unexpected problems");}
            }
        }
    }

    public static class UserHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exc) throws IOException {
            exc.getResponseHeaders().add("Access-Control-Allow-Headers","Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            exc.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");

            String query = Support.toParseRequest(exc);

            if("GET".equals(exc.getRequestMethod())){
                try{
                    ArrayList<User> getUsers = Facade.allUsers;
                    Support.makeResponse(exc, getUsers);
                    DatabaseConection.closeConnection();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                if ("POST".equals(exc.getRequestMethod())){
                    try {
                        String createData = CrudData.createNewUser(query, Facade.allUsers, Facade.allHostels);
                        Support.makeResponse(exc, createData);
                        DatabaseConection.closeConnection();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else{Support.makeResponse(exc, "Unexpected problems");}
            }
        }
    }

    public static class HostelsHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exc) throws IOException {
            exc.getResponseHeaders().add("Access-Control-Allow-Headers","Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            exc.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, PATCH");

            String query = Support.toParseRequest(exc);

            if("GET".equals(exc.getRequestMethod())){
                try{
                    ArrayList<Hostel> getHostel = Facade.allHostels;
                    Support.makeResponse(exc, getHostel);
                    DatabaseConection.closeConnection();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                if ("PATCH".equals(exc.getRequestMethod())){
                    try{
                        String updateData = CrudData.updateHostel(query, Facade.allHostels);
                        Support.makeResponse(exc, updateData);
                        DatabaseConection.closeConnection();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else{Support.makeResponse(exc, "Unexpected problems");}
            }
        }
    }

}
