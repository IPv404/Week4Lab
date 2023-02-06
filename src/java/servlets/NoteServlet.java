/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author Zeina Mint
 */
public class NoteServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        //read first line, set as title
        String title = br.readLine();
        //read second line, content
        String contents = br.readLine();
        
        //close
        br.close();
        Note note = new Note(); 
        
        request.setAttribute("note", note);
        
        note.setTitle(title);
        note.setContents(contents);
        
          String edit = request.getParameter("edit");
          
          if(edit != null){
              getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            return;
          }
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        //set title/contents
        
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        
        //create new note obj and set attribute
        Note note = new Note();
        request.setAttribute("note", note);
        
        //write in note
        PrintWriter edit = new PrintWriter(new BufferedWriter(
                new FileWriter(path, false)));
        
        edit.print(note.getTitle());
        edit.print(note.getContents());
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);
    }
}