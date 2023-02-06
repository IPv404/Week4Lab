
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
        
        Note note = new Note(); 
        
        request.setAttribute("note", note);
        
        note.setTitle(title);
        note.setContents(contents);
        
        br.close();

        
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
        
        //CODE BY Azrie
        //Examining their code really helped me understand why it wasnt saving properly!
        Note note = new Note(request.getParameter("title"),request.getParameter("contents"));
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter pw; 
        pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        pw.println(note.getTitle());
        pw.print(note.getContents());
        pw.close();
        
        note.setContents(note.getContents().replace("\n", "<br>"));
        
        request.setAttribute("note", note);
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);
    }
}