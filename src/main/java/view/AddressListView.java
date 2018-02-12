package view;

import java.util.Iterator;
import model.AddressListModel;
import model.AddressModel;


/**
 *
 * @author dr
 */
public class AddressListView extends View {

    private final AddressListModel database;

    public AddressListView(AddressListModel database) {
        this.database = database;
    }

    @Override
    public String makeHTML() {

        String html = "<html><body>";

        // code to make a table goes here
        // <table> ... </table>
        // each row:  <tr><td>John Doe</td><td>1000 Atherton St</td></tr>

        html += "<ul>";
        Iterator<AddressModel> addresses = database.getAddresses();
        while (addresses.hasNext()) {
            AddressView av = new AddressView(addresses.next());
            html += av.makeBulletHTML();
        }

        html += "</ul>";
	
        
        html += "</body></html>";

        return html;

    }

}
