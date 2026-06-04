package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.JTextField;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfDirector = new JTextField(20);
    private final JTextField tfLength = new JTextField(20);

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD");
        addRow("Director", tfDirector);
        addRow("Length", tfLength);
        showScreen();
    }

    @Override
    protected Media createMedia() {
        return new DigitalVideoDisc(
                readInt(tfId, "Id"),
                readRequired(tfTitle, "Title"),
                readRequired(tfCategory, "Category"),
                readFloat(tfCost, "Cost"),
                readInt(tfLength, "Length"),
                readRequired(tfDirector, "Director")
        );
    }
}
