package view;


/**
 *
 * @author dr
 */
public class NotFoundView extends View {

    public NotFoundView() {
    }
    @Override
    public boolean isOKStatus () {
        return false;
    }
    @Override
    public String makeHTML() {

        String html = "<html><body>";

        html += "<h1>Error 404.</h1>";
        html += "<p>This page is not available.</p>";
        
        html += "</body></html>";

        return html;
        
    }
    
}
