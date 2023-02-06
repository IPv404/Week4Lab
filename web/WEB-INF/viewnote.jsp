<%-- 
    Document   : viewnote
    Created on : 5-Feb-2023, 4:27:10 PM
    Author     : Zeina Mint
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>View Note</h2>
        
        <p>
             <b>Title: </b> ${note.title}
        </p>
        
        <p>
            <b>Contents:</b><br>
            ${note.contents}
        </p>
        
        
        <a href="note?edit">Edit</a>
        
        <%--
        
        Title: <contents edit>
        contents: <br>
        <contents edit>
        <edit ref>
        
        --%>
    </body>
</html>
